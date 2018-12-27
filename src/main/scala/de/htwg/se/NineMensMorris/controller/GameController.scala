package de.htwg.se.NineMensMorris.controller

import de.htwg.se.NineMensMorris.controller.impl.Error
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player

import scala.swing.Publisher

trait GameController extends Publisher{

  def createGameboard(): Unit

  def addPlayer(player1: String, player2: String): Unit

  def performTurn(startFieldId: Int, targetFieldId: Int): Error.Value

  def changePlayerOnTurn(): Unit

  def getPlayerOnTurnPhase: PlayerGamePhase
}
