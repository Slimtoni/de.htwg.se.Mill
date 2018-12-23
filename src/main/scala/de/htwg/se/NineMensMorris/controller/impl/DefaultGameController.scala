package de.htwg.se.NineMensMorris.controller.impl

import de.htwg.se.NineMensMorris.controller.GameController
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
      case Some(value) => {
        //println("Phase of Value:" + value.phase)
        playerOnTurn = value
        publish(new PlayerPhaseChanged)
      }
      case None => publish(new GamePhaseChanged)
    }
  }

  // wenn ein Spieler eine Mühle schließt wechselt der Spieler nicht!!!

  override def performTurn(startFieldID: Int, targetFieldID: Int): Unit = {
    getPlayerOnTurnPhase match {
      case PlayerGamePhase.Place => placeMan(startFieldID)
      case PlayerGamePhase.Move => moveMan(startFieldID, targetFieldID)
      case PlayerGamePhase.Fly => flyMan(startFieldID, targetFieldID)
    }
    endPlayersTurn()
  }

  override def getPlayerOnTurnPhase: PlayerGamePhase = playerOnTurn.phase

  private def placeMan(targetFieldId: Int): Unit = {
    playerOnTurn.incrementPlacedMen()
    playerOnTurn.name match {
      case "Black" => {
        //println("Player: " + playerOnTurn.name + " placed men: " + playerOnTurn.numberPlacedMen)
        changeFieldStatus(targetFieldId, "Black")
      }
      case "White" => {
        //println("Player: " + playerOnTurn.name + " placed men: " + playerOnTurn.numberPlacedMen)
        changeFieldStatus(targetFieldId, "White")
      }
      case _ => changeFieldStatus(targetFieldId, "Empty")
    }

  }

  private def moveMan(startFieldId: Int, targetFieldId: Int): Unit = {
    val startField: Field = gameboard.getField(startFieldId)
    val targetField: Field = gameboard.getField(targetFieldId)
    if (startField.id != 99 && targetField.id != 99 && startField.fieldStatus.toString == playerOnTurn.name) {
      if (gameboard.containsEdge(startField,targetField)) {
        changeFieldStatus(startFieldId, "Empty")
        changeFieldStatus(targetField.id, playerOnTurn.name)
      } else publish(new MissingEdgeError)
    } else publish(new InvalidFieldError)
    //if (gameboard.containsEdge(startField, gameboard.getField(targetFieldId)))
  }

  private def flyMan(startFieldId: Int, targetFieldId: Int): Unit = ???

  private def changeFieldStatus(field: Int, fieldStatus: String): Unit = {
    var gameboardNew = gameboard.set(field,fieldStatus)
    gameboardNew match {
      case Some(gameb) => {
        gameboard = gameb
      }
      case None => publish(new Error("Invalid Field")) //TODO: passenden Error definieren
    }
  }

  def endPlayersTurn(): Unit = {
    changePlayerOnTurn()
    publish(new FieldChanged)
  }

  override def changePlayerOnTurn(): Unit = {
    if (playerOnTurn == playerWhite) {
      playerWhite = playerOnTurn
      playerOnTurn = playerBlack
    }
    else {
      playerBlack = playerOnTurn
      playerOnTurn = playerWhite
    }
    publish(new CurrentPlayerChanged)
  }
}