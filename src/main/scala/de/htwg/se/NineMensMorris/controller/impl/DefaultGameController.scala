package de.htwg.se.NineMensMorris.controller.impl

import de.htwg.se.NineMensMorris.controller.GameController
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model._

class DefaultGameController(var gameboard: Gameboard) extends GameController {
  var gameboardFactory = new GameboardFactory
  var player1: Player = _
  var player2: Player = _
  var playerOnTurn: Player = _


  def gameboardToString: String = gameboard.toString

  def createGameboard(): Unit = {
    gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    addPlayer("White", "Black")
    playerOnTurn = player1
    publish(new FieldChanged)
  }

  override def addPlayer(sPlayer1: String, sPlayer2: String): Unit = {
    player1 = Player(sPlayer1, PlayerGamePhase.Place, 0)
    player2 = Player(sPlayer2, PlayerGamePhase.Place, 0)
  }

  override def performTurn(playerOnTurn: Player, targetFieldID: Int): Unit = {
    if (!playerOnTurn.checkPlacedMen()) {
      publish(new GamePhaseChanged)
    }

    placeMan(targetFieldID)
    // wenn ein Spieler eine Mühle schließt wechselt der Spieler nicht!!!
  }

  override def performTurn(playerOnTurn: Player, startFieldID: Int, targetFieldID: Int): Unit = {
    if (!playerOnTurn.checkPlacedMen()) {
      publish(new GamePhaseChanged)
    }
    if (playerOnTurn.numberPlacedMen < 3) {
      publish(new GameOver)
    }

    playerOnTurn.phase match {
      case PlayerGamePhase.Move => moveMan(startFieldID, targetFieldID)
      case PlayerGamePhase.Fly => flyMan(startFieldID, targetFieldID)
    }
  }

  override def getPlayerOnTurnPhase: PlayerGamePhase = playerOnTurn.phase

  private def placeMan(targetFieldId: Int): Unit = {
    playerOnTurn.name match {
      case "Black" => changeFieldStatus(targetFieldId, "Black")
      case "White" => changeFieldStatus(targetFieldId, "White")
      case _ => changeFieldStatus(targetFieldId, "Empty")
    }
  }

  private def moveMan(startFieldId: Int, targetFieldId: Int): Unit = ???

  private def flyMan(startFieldId: Int, targetFieldId: Int): Unit = ???

  private def changeFieldStatus(field: Int, fieldStatus: String): Unit = {
    var gameboardNew = gameboard.set(field, fieldStatus)
    gameboardNew match {
      case Some(gameb) => {
        gameboard = gameb
        changePlayerOnTurn()
        publish(new FieldChanged)
      }
      case None => publish(new Error) //TODO: passenden Error definieren
    }
  }

  override def changePlayerOnTurn(): Unit = {
    if (playerOnTurn == player1) playerOnTurn = player2
    else playerOnTurn = player1
    publish(new CurrentPlayerChanged)
  }
}