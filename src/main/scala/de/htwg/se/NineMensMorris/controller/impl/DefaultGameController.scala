package de.htwg.se.NineMensMorris.controller.impl

import de.htwg.se.NineMensMorris.controller.{GameController, impl}
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model._

class DefaultGameController(var gameboard: Gameboard) extends GameController {
  var gameboardFactory = new GameboardFactory
  var playerWhite: Player = _
  var playerBlack: Player = _
  var playerOnTurn: Player = _

  def gameboardToString: String = gameboard.toString

  def createGameboard(): Unit = {
    gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    addPlayer("White", "Black")
    playerOnTurn = playerWhite
    publish(new FieldChanged)
  }

  override def addPlayer(sPlayerWhite: String, sPlayerBlack: String): Unit = {
    playerWhite = Player(sPlayerWhite, PlayerGamePhase.Place, 0)
    playerBlack = Player(sPlayerBlack, PlayerGamePhase.Place, 0)
  }

  def checkPlayer(player: Player): Unit = {
    player.checkPlacedMen() match {
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
    getPlayerOnTurnPhase match {
      case PlayerGamePhase.Place => err = placeMan(startFieldID)
      case PlayerGamePhase.Move => err = moveMan(startFieldID, targetFieldID)
      case PlayerGamePhase.Fly => err = flyMan(startFieldID, targetFieldID)
    }
    if (err == Error.NoError) endPlayersTurn()
    err
  }

  override def getPlayerOnTurnPhase: PlayerGamePhase = playerOnTurn.phase

  private def placeMan(targetFieldId: Int): Error.Value = {
    val targetField: Field = gameboard.getField(targetFieldId)
    if (targetField.fieldStatus == FieldStatus.Empty) {
      val error = changeFieldStatus(targetFieldId, playerOnTurn.name)
      if (error == Error.NoError) playerOnTurn.incrementPlacedMen()
      return error
    }
    Error.FieldError
  }

  private def moveMan(startFieldId: Int, targetFieldId: Int): Error.Value = {
    val startField: Field = gameboard.getField(startFieldId)
    val targetField: Field = gameboard.getField(targetFieldId)
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

  private def flyMan(startFieldId: Int, targetFieldId: Int): Error.Value = ???

  private def changeFieldStatus(field: Int, fieldStatus: String): impl.Error.Value = {
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
    if (playerOnTurn == playerWhite) {
      //playerWhite = playerOnTurn
      playerOnTurn = playerBlack
    }
    else {
      //playerBlack = playerOnTurn
      playerOnTurn = playerWhite
    }
    publish(new CurrentPlayerChanged)
  }
}