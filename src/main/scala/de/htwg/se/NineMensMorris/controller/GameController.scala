package de.htwg.se.NineMensMorris.controller

import de.htwg.se.NineMensMorris.model.Player
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase

import scala.swing.Publisher

trait GameController extends Publisher{

  def createGameboard(): Unit

  def addPlayer(player1: String, player2: String): Unit

  def performTurn(playerOnTurn: Player, targetId: Int)

  def performTurn(playerOnTurn: Player, startFieldId: Int, targetFieldId: Int)

  /*def placeMan(fieldId: Int): Unit

  def moveMan(startFieldId: Int, targetFieldId: Int): Unit

  def flyMan(startFieldId: Int, targetFieldId: Int): Unit*/

  def changePlayerOnTurn(): Unit

  def getPlayerOnTurnPhase: PlayerGamePhase
}
