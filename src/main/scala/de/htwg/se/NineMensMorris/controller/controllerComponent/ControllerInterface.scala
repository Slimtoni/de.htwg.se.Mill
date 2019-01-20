package de.htwg.se.NineMensMorris.controller.controllerComponent

import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

import scala.collection.mutable
import scala.swing.event.Event
import scala.swing.Publisher

trait ControllerInterface extends Publisher{

  def playerOnTurn: PlayerInterface
  def playerWhite: PlayerInterface
  def playerBlack: PlayerInterface
  def createGameboard(): Unit
  def startNewGame(): Unit
  def addPlayer(player1: String, player2: String): Unit
  def performTurn(startFieldId: Int, targetFieldId: Int): Error.Value
  def changePlayerOnTurn(): Unit
  def getPlayerOnTurnPhase: String
  def getPlayerOnTurn: String
  def getVertexList: mutable.MutableList[FieldInterface]
  def getNeigh: mutable.MutableList[EdgeInterface]
  def getField(id: Int): Option[FieldInterface]
  def checkMill(field: Int): Boolean
  def allMenInMill(): Boolean
  def gameOver(): Boolean
  def endPlayersTurn(): Unit
  def caseOfMill(fieldtmp: Int): Error.Value
  def save(fileS: String):Error.Value
  def load(fileS:String): Error.Value
  def gameboardToString: String
}

class FieldChanged extends Event
class GameOver extends Event
class PlayerPhaseChanged extends Event
class CaseOfMill extends Event
class StartNewGame extends Event

