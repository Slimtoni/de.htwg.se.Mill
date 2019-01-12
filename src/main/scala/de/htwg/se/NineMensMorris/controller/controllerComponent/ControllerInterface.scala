package de.htwg.se.NineMensMorris.controller.controllerComponent

import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface}

import scala.collection.mutable
import scala.swing.event.Event
import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def createGameboard(): Unit
  def addPlayer(player1: String, player2: String): Unit
  def checkPlayer(player: String): Unit
  def performTurn(startFieldId: Int, targetFieldId: Int): Error.Value
  def changePlayerOnTurn(): Unit
  def getPlayerOnTurnPhase: String
  def getPlayerOnTurn: String
  def getVertexList: mutable.MutableList[FieldInterface]
  def getNeigh: mutable.MutableList[EdgeInterface]
  def getField(id: Int): Option[FieldInterface]
  def checkMill(field: Int): Boolean
  def endPlayersTurn(): Unit
}

class FieldChanged extends Event
class GamePhaseChanged extends Event
class PlayerPhaseChanged extends Event
class CurrentPlayerChanged extends Event
class CaseOfMill extends Event

