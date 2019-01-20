package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.NineMensMorris.NineMensMorrisModule
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.{FieldStatus, FileIOInterface, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.EdgeInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.{FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player


import scala.collection.mutable


class ControllerMill @Inject() (var gameboard: GameboardInterface) extends ControllerInterface {

  var gameboardFactory = new GameboardFactory
  var playerWhite: PlayerInterface = _
  var playerBlack: PlayerInterface = _
  var playerOnTurn: PlayerInterface = _
  var players: (PlayerInterface, PlayerInterface) = _
  var gameStarted = false
  val injector = Guice.createInjector(new NineMensMorrisModule)
  val fileIo = injector.instance[FileIOInterface]

  var gameOver = false

  def gameboardToString: String = gameboard.toString


  def save(fileS: String): Error.Value = {


    fileIo.save(fileS, gameboard, (playerWhite, playerBlack, playerOnTurn))


  }

  def load(fileS: String): Error.Value = {

    val tmp = fileIo.load(fileS)

    tmp match {
      case None => Error.LoadError

      case Some(game) =>
        gameboard = game._1
        playerWhite = game._2._1
        playerBlack = game._2._2
        playerOnTurn = game._2._3
        publish(new FieldChanged)
        publish(new StartNewGame)
        Error.NoError

    }


  }


  def createGameboard(): Unit = {
    gameboard = gameboardFactory.createGameboard(GameboardSize.Nine)
    addPlayer("White", "Black")
    val gameboardtmp = gameboard.setNeigh()
    gameboardtmp match {
      case Some(gmb) => {
        gameboard = gmb
      }
        Error.FieldError
    }
    playerOnTurn = playerWhite
    publish(new FieldChanged)
  }

  override def startNewGame(): Unit = {
    createGameboard()
    gameOver = false
    publish(new StartNewGame)
  }

  override def addPlayer(sPlayerWhite: String, sPlayerBlack: String): Unit = {
    playerWhite = Player(sPlayerWhite, PlayerGamePhase.Place, 0, 0)
    playerBlack = Player(sPlayerBlack, PlayerGamePhase.Place, 0, 0)
    players = (playerWhite, playerBlack)
  }

  override def performTurn(startFieldID: Int, targetFieldID: Int): Error.Value = {
    var err = Error.NoError
    playerOnTurn.phase match {
      case PlayerGamePhase.Place => err = placeMan(startFieldID)
      case PlayerGamePhase.Move => err = moveMan(startFieldID, targetFieldID)
      case PlayerGamePhase.Fly => err = flyMan(startFieldID, targetFieldID)
    }
    err
  }

  override def getPlayerOnTurnPhase: String = playerOnTurn.phase.toString

  override def getPlayerOnTurn: String = playerOnTurn.name

  def placeMan(targetFieldId: Int): Error.Value = {
    val targetField: FieldInterface = gameboard.getField(targetFieldId)
    if (targetField.fieldStatus == FieldStatus.Empty) {
      val error = changeFieldStatus(targetFieldId, playerOnTurn.name)
      if (error == Error.NoError) playerOnTurn.incrementPlacedMen()
      return error
    }
    Error.FieldError
  }

  def moveMan(startFieldId: Int, targetFieldId: Int): Error.Value = {
    val startField: FieldInterface = gameboard.getField(startFieldId)
    val targetField: FieldInterface = gameboard.getField(targetFieldId)
    if (startField.fieldStatus.toString == playerOnTurn.name
      && targetField.fieldStatus == FieldStatus.Empty) {
      if (gameboard.containsEdge(startField, targetField)) {
        var err: Error.Value = changeFieldStatus(startFieldId, "Empty")
        if (err == Error.NoError) err = changeFieldStatus(targetField.id, playerOnTurn.name)
        err
      } else Error.EdgeError
    } else Error.FieldError
  }

  def flyMan(startFieldId: Int, targetFieldId: Int): Error.Value = {
    val startField: FieldInterface = gameboard.getField(startFieldId)
    val targetField: FieldInterface = gameboard.getField(targetFieldId)
    if (startField.fieldStatus.toString == playerOnTurn.name
      && targetField.fieldStatus == FieldStatus.Empty) {
      var err: Error.Value = changeFieldStatus(startFieldId, "Empty")
      if (err == Error.NoError) err = changeFieldStatus(targetFieldId, playerOnTurn.name)
      err
    } else Error.FieldError
  }

  def changeFieldStatus(field: Int, fieldStatus: String): Error.Value = {
    val gameboardNew = gameboard.set(field, fieldStatus)
    gameboardNew match {
      case Some(gameb) =>
        gameboard = gameb
        val gameboardtmp = gameboard.setNeigh()
        gameboardtmp match {
          case Some(gmb2) => {
            gameboard = gmb2
          }
            Error.FieldError
        }
        Error.NoError
      case None => Error.FieldError
    }
  }


  override def checkMill(fieldtmp: Int): Boolean = {
    val field: FieldInterface = gameboard.getField(fieldtmp)
    val checkCol: FieldStatus = field.fieldStatus
    if (field.millneigh.head._1.fieldStatus == checkCol && field.millneigh.head._2.fieldStatus == checkCol && checkCol != FieldStatus.Empty ||
      field.millneigh(1)._1.fieldStatus == checkCol && field.millneigh(1)._2.fieldStatus == checkCol && checkCol != FieldStatus.Empty) {
      true
    } else false
  }

  override def allMenInMill(): Boolean = {
    var check = false
    for (i <- getVertexList.iterator) {
      if (playerOnTurn.equals(playerWhite) && i.fieldStatus == FieldStatus.Black) {
        check = checkMill(i.id)
      } else if (playerOnTurn.equals(playerBlack) && i.fieldStatus == FieldStatus.White) {
        check = checkMill(i.id)
      }
    }
    check
  }

  override def caseOfMill(fieldtmp: Int): Error.Value = {
    if (!allMenInMill()) {
      val field: FieldInterface = gameboard.getField(fieldtmp)
      if (playerOnTurn.equals(playerWhite)) {
        if (field.fieldStatus == FieldStatus.White || field.fieldStatus == FieldStatus.Empty) {
          return Error.SelectError
        } else {
          if (!checkMill(fieldtmp)) {
            killMan(fieldtmp)
            return Error.NoError
          }
        }
      } else if (playerOnTurn.equals(playerBlack)) {
        if (field.fieldStatus == FieldStatus.Black || field.fieldStatus == FieldStatus.Empty) {
          return Error.SelectError
        } else {
          if (!checkMill(fieldtmp)) {
            killMan(fieldtmp)
            return Error.NoError
          }
        }
      }
      Error.SelectError
    } else {
      Error.KillManError
    }
  }

  def killMan(fieldId: Int): Unit = {
    val error = changeFieldStatus(fieldId, "Empty")
    if (error == Error.NoError) {
      if (playerOnTurn.equals(playerWhite))
        playerBlack.incrementLostMen()
      else {
        playerWhite.incrementLostMen()
      }
      publish(new FieldChanged)
    }

  }

  def endPlayersTurn(): Unit = {
    changePlayerOnTurn()
    if (playerOnTurn.checkPlayerLost()) {
      gameOver = true
      publish(new GameOver)
    } else {
      playerOnTurn.checkedPlacedMen() match {
        case Some(value) => playerOnTurn = value
      }
      publish(new FieldChanged)
    }
  }

  override def changePlayerOnTurn(): Unit

  = {
    if (playerOnTurn.equals(playerWhite)) {
      playerWhite = playerOnTurn
      playerOnTurn = playerBlack
    }
    else {
      playerBlack = playerOnTurn
      playerOnTurn = playerWhite
    }
  }



  def getVertexList: mutable.MutableList[FieldInterface] = {
    gameboard.vertexList
  }

  def getNeigh: mutable.MutableList[EdgeInterface] = {
    gameboard.neigh
  }

  override def getField(id: Int): Option[FieldInterface] = {
    val field = gameboard.getField(id)
    if (field.id != 99) Some(field)
    else None
  }
}