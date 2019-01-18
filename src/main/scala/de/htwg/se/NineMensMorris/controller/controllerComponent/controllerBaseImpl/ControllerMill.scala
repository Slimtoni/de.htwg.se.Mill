package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl

import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.gameboardComponent.{FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Gameboard

import scala.collection.mutable
import java.nio.file.Files
import java.nio.file.Paths
import de.htwg.se.NineMensMorris.model.{FileIOComponents, FileIOInterface}

class ControllerMill(var gameboard: GameboardInterface) extends ControllerInterface {

  var gameboardFactory = new GameboardFactory
  var playerWhite: PlayerInterface = _
  var playerBlack: PlayerInterface = _
  var playerOnTurn: PlayerInterface = _
  var players: (PlayerInterface, PlayerInterface) = _
  var gameStarted = false


  def gameboardToString: String = gameboard.toString


  def save(): Unit = {
    savee(gameboard, (playerWhite, playerBlack, playerOnTurn))
  }


  def load() /*Option[(GameboardInterface, (PlayerInterface, PlayerInterface, PlayerInterface))]*/ = {
    var gameboard = new Gameboard()


    val file = scala.xml.XML.loadFile("mill.xml")
    val player1name = (file \\ "player1" \\ "name").text.trim
    val player2name = (file \\ "player2" \\ "name").text.trim
    val playerOnTurnXML = (file \\ "playerOnTurn").text.trim

    var player1phase = (file \\ "player1" \\ "phase").text.trim match {
      case "Place" => PlayerGamePhase.Place
      case "Move" => PlayerGamePhase.Move
      case "Fly" => PlayerGamePhase.Fly
    }

    var player2phase = (file \\ "player2" \\ "phase").text.trim match {
      case "Place" => PlayerGamePhase.Place
      case "Move" => PlayerGamePhase.Move
      case "Fly" => PlayerGamePhase.Fly
    }

    val player1PlacedMen = (file \\ "player1" \\ "placedMen").text.trim.toInt
    val player2PlacedMen = (file \\ "player2" \\ "placedMen").text.trim.toInt

    val player1LostMen = (file \\ "player1" \\ "lostMen").text.trim.toInt
    val player2LostMen = (file \\ "player2" \\ "lostMen").text.trim.toInt


    val player1 = Player(player1name, player1phase, player1PlacedMen, player1LostMen)
    val player2 = Player(player2name, player2phase, player2PlacedMen, player2LostMen)
    var currentPlayer: PlayerInterface = null
    if (playerOnTurnXML.equals(player1.name)) {
      currentPlayer = player1
    } else {
      currentPlayer = player2
    }


    println(player1.name + " " + player1.phase + " " + player1.numberPlacedMen + " " + player1.numberLostMen)


    val vertexXml = (file \\ "vertexList").text.trim
    for (x <- 0 to vertexXml.length - 1) {
      var status: FieldStatus = null
      if (vertexXml.charAt(x) == 'O') {
        status = FieldStatus.Empty
      } else if (vertexXml.charAt(x) == 'W') {
        status = FieldStatus.White
      } else if (vertexXml.charAt(x) == 'B') {
        status = FieldStatus.Black
      } else {
        println("WTF")
      }
      var tmpField = Field(x, status, mutable.MutableList.empty)
      gameboard.addVertex(tmpField)

    }

    println(gameboard)

    println(currentPlayer)

    val gameboardtmp = gameboard.setNeigh()
    gameboardtmp match {
      case Some(gmb) => {
        gameboard = gmb
      }
        Error.LoadError
    }
    if (vertexXml.length == 24) {
      gameboard = gameboardFactory.setEdgeList(GameboardSize.Nine, gameboard)
    }
    println(gameboard.neigh)
    println("load complete")

    //Some(gameboard, (player1, player2, currentPlayer))
  }

  def savee(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Unit = {
    val file = new File("mill.xml")
    scala.xml.XML.save("mill.xml", millToXML(gameboard, player))
    println("saved file")
    println(gameboard.neigh)
    load()
  }

  def millToXML(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)) = {
    <NineMensMorris>
      {gameboardToXML(gameboard)}{playerToXML(player)}
    </NineMensMorris>
  }


  def gameboardToXML(gameboard: GameboardInterface) = {
    <gameboard>
      <vertexList>
        {gameboard.vertexList}
      </vertexList>
      <edgeList>
        {gameboard.neigh}
      </edgeList>
    </gameboard>
  }


  def playerToXML(player: (PlayerInterface, PlayerInterface, PlayerInterface)) = {
    <players>
      <playerOnTurn>
        {player._3}
      </playerOnTurn>
      <player1>
        <name>
          {player._1.name}
        </name>
        <phase>
          {player._1.phase}
        </phase>
        <placedMen>
          {player._1.numberPlacedMen}
        </placedMen>
        <lostMen>
          {player._1.numberLostMen}
        </lostMen>
      </player1>
      <player2>
        <name>
          {player._2.name}
        </name>
        <phase>
          {player._2.phase}
        </phase>
        <placedMen>
          {player._2.numberPlacedMen}
        </placedMen>
        <lostMen>
          {player._2.numberLostMen}
        </lostMen>
      </player2>
    </players>

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
    publish(new StartNewGame)
  }

  override def addPlayer(sPlayerWhite: String, sPlayerBlack: String): Unit = {
    playerWhite = Player(sPlayerWhite, PlayerGamePhase.Place, 0, 0)
    playerBlack = Player(sPlayerBlack, PlayerGamePhase.Place, 0, 0)
    players = (playerWhite, playerBlack)
  }

  def checkPlayer(splayer: String): Unit = {
    val player: PlayerInterface = getPlayer(splayer)
    player.checkedPlacedMen() match {
      case Some(value) =>
        //println("Phase of Value:" + value.phase)
        playerOnTurn = value
        publish(new PlayerPhaseChanged)
      case None => publish(new GamePhaseChanged)
    }
  }

  // wenn ein Spieler eine Mühle schließt wechselt der Spieler nicht!!!

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
    //if (gameboard.containsEdge(startField, gameboard.getField(targetFieldId)))
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


  def checkMill(fieldtmp: Int): Boolean = {
    val field: FieldInterface = gameboard.getField(fieldtmp)
    val checkCol: FieldStatus = field.fieldStatus
    if (field.millneigh(0)._1.fieldStatus == checkCol && field.millneigh(0)._2.fieldStatus == checkCol && checkCol != FieldStatus.Empty ||
      field.millneigh(1)._1.fieldStatus == checkCol && field.millneigh(1)._2.fieldStatus == checkCol && checkCol != FieldStatus.Empty) {
      true
    } else false
  }


  override def caseOfMill(fieldtmp: Int): Error.Value = {
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
  }

  def killMan(fieldId: Int): Unit = {
    val field: FieldInterface = gameboard.getField(fieldId)
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
    publish(new FieldChanged)
  }

  override def changePlayerOnTurn(): Unit = {
    if (playerOnTurn.equals(playerWhite)) {
      playerWhite = playerOnTurn
      playerOnTurn = playerBlack
    }
    else {
      playerBlack = playerOnTurn
      playerOnTurn = playerWhite
    }
  }

  def getPlayer(name: String): PlayerInterface = {
    if (players._1.name == name) players._1
    else players._2
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