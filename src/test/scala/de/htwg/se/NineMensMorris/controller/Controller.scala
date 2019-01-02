package de.htwg.se.NineMensMorris.controller

import de.htwg.se.NineMensMorris.NineMensMorris.gameboardFactory
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import de.htwg.se.NineMensMorris.model.gameboardComponent.{FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player

class Controller extends WordSpec with Matchers {
  var player1: PlayerInterface = _
  var player2: PlayerInterface = _
  var playerOnTurn: PlayerInterface = _
  var gameboardFactory = new GameboardFactory
  var gameboard: GameboardInterface = gameboardFactory.createGameboard(GameboardSize.Large)
  var players: (PlayerInterface, PlayerInterface) = _
  val controller = new Controller(gameboardFactory.createGameboard(GameboardSize.Large))



  "A Controller" when {
    "new" should {
      controller.cre
    }
  }


}
