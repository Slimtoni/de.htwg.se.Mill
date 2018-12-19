package de.htwg.se.NineMensMorris.controller

import scala.swing.Publisher

trait GameController extends Publisher{
  def addPlayer(players: String*): Unit

  def changeFieldStatus(id: Int, status: String): Unit

  def createGameboard(): Unit
}
