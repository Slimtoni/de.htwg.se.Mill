package de.htwg.se.NineMensMorris.controller.controllerComponent

import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import scala.swing.event.Event
import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def createGameboard(): Unit
  def addPlayer(player1: String, player2: String): Unit
  def performTurn(startFieldId: Int, targetFieldId: Int): Error.Value
  def changePlayerOnTurn(): Unit
  def getPlayerOnTurnPhase: String
  def getPlayerOnTurn: String
}

class FieldChanged extends Event
class GamePhaseChanged extends Event
class PlayerPhaseChanged extends Event
class CurrentPlayerChanged extends Event
class CaseOfMill extends Event

