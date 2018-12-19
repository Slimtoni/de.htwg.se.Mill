package de.htwg.se.NineMensMorris.controller.impl

import de.htwg.se.NineMensMorris.controller.GameController
import de.htwg.se.NineMensMorris.model.{Gameboard, GameboardFactory, GameboardSize}

class DefaultGameController(var gameboard: Gameboard) extends GameController {
  var gameboardFactory = new GameboardFactory

  def gameboardToString: String = gameboard.toString

  def createGameboard(): Unit = {
    gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    publish(new FieldChanged)
  }

  override def changeFieldStatus(field: Int, fieldStatus: String): Unit = {
    gameboard = gameboard.set(field,fieldStatus)
    publish(new FieldChanged)
  }

  override def addPlayer(players: String*): Unit = println("Not implemented yet")
}