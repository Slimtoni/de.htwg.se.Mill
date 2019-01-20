package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerMockImpl

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface}
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardMockImpl.Gameboard
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerMockImpl.Player
import de.htwg.se.NineMensMorris.controller.controllerComponent.Error

import scala.collection.mutable

class Controller(var gameboard: GameboardInterface) extends ControllerInterface {

  gameboard = new Gameboard()

  override def createGameboard(): Unit = {}

  override def addPlayer(player1: String, player2: String): Unit = {}

  override def performTurn(startFieldId: Int, targetFieldId: Int): controllerComponent.Error.Value
  = controllerComponent.Error.NoError

  override def changePlayerOnTurn(): Unit = {}

  override def getPlayerOnTurnPhase: String = PlayerGamePhase.Place.toString

  override def getPlayerOnTurn: String = "White"

  override def getVertexList: mutable.MutableList[FieldInterface] = mutable.MutableList.empty

  override def getNeigh: mutable.MutableList[EdgeInterface] = mutable.MutableList.empty

  override def getField(id: Int): Option[FieldInterface] = None

  override def checkMill(field: Int): Boolean = false

  override def endPlayersTurn(): Unit = {}

  override def startNewGame(): Unit = {}

  override def caseOfMill(fieldtmp: Int): controllerComponent.Error.Value = controllerComponent.Error.NoError

  override def save(fileS: String): Error.Value = Error.NoError

  override def load(fileS: String): Error.Value = Error.NoError

  override def gameOver(): Boolean = false

  override def playerOnTurn: PlayerInterface = playerWhite

  override def playerWhite: PlayerInterface = new Player()

  override def playerBlack: PlayerInterface = new Player()

  override def gameboardToString: String = ""

  override def allMenInMill(): Boolean = false
}
