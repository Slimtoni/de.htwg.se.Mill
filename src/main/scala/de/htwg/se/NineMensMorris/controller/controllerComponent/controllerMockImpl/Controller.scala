package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerMockImpl

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardInterface}
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardMockImpl.Gameboard

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

  override def checkPlayer(player: String): Unit = {}

  override def getField(id: Int): Option[FieldInterface] = None

  override def checkMill(field: Int): Boolean = false

  override def endPlayersTurn(): Unit = {}
}
