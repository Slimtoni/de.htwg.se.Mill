package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import de.htwg.se.NineMensMorris.model.gameboardComponent.{FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player

class Controller(var gameboard: GameboardInterface) extends ControllerInterface {
  var gameboardFactory = new GameboardFactory
  var playerWhite: PlayerInterface = _
  var playerBlack: PlayerInterface = _
  var playerOnTurn: PlayerInterface = _
  var players: (PlayerInterface, PlayerInterface) = _

  def gameboardToString: String = gameboard.toString

  def createGameboard(): Unit = {
    gameboard = gameboardFactory.createGameboard(GameboardSize.Nine)
    addPlayer("White", "Black")
    playerOnTurn = playerWhite
    publish(new FieldChanged)
  }

  override def addPlayer(sPlayerWhite: String, sPlayerBlack: String): Unit = {
    playerWhite = Player(sPlayerWhite, PlayerGamePhase.Place, 0)
    playerBlack = Player(sPlayerBlack, PlayerGamePhase.Place, 0)
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
    if (err == Error.NoError) endPlayersTurn()
    err
  }

  override def getPlayerOnTurnPhase: String = playerOnTurn.phase.toString

  override def getPlayerOnTurn: String = playerOnTurn.name

  private def placeMan(targetFieldId: Int): Error.Value = {
    val targetField: FieldInterface = gameboard.getField(targetFieldId)
    if (targetField.fieldStatus == FieldStatus.Empty) {
      val error = changeFieldStatus(targetFieldId, playerOnTurn.name)
      if (error == Error.NoError) playerOnTurn.incrementPlacedMen()
      return error
    }
    Error.FieldError
  }

  private def moveMan(startFieldId: Int, targetFieldId: Int): Error.Value = {
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

  private def flyMan(startFieldId: Int, targetFieldId: Int): Error.Value = {
    val startField: FieldInterface = gameboard.getField(startFieldId)
    val targetField: FieldInterface = gameboard.getField(targetFieldId)
    if (startField.fieldStatus.toString == playerOnTurn.name
        && targetField.fieldStatus == FieldStatus.Empty) {
      var err: Error.Value = changeFieldStatus(startFieldId, "Empty")
      if (err == Error.NoError) err = changeFieldStatus(targetFieldId, playerOnTurn.name)
      err
    } else Error.FieldError
  }

  private def changeFieldStatus(field: Int, fieldStatus: String): Error.Value = {
    val gameboardNew = gameboard.set(field, fieldStatus)
    gameboardNew match {
      case Some(gameb) => {
        gameboard = gameb
        Error.NoError
      }
      case None => Error.FieldError
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
    publish(new CurrentPlayerChanged)
  }

  private def getPlayer(name: String): PlayerInterface = {
    if (players._1.name == name) players._1
    else players._2
  }
}