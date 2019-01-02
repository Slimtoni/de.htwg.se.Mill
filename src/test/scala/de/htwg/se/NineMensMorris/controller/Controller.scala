package de.htwg.se.NineMensMorris.controller

import de.htwg.se.NineMensMorris.NineMensMorris.gameboardFactory
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.{ControllerMill}
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import de.htwg.se.NineMensMorris.model.gameboardComponent.{FieldInterface, GameboardFactory, GameboardInterface}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player

class Controller extends WordSpec with Matchers {
  var playerWhite: PlayerInterface = _
  var playerBlack: PlayerInterface = _
  var playerTest: PlayerInterface = new Player("White", PlayerGamePhase.Place, 0)
  var playerOnTurn: PlayerInterface = _
  var gameboardFactory = new GameboardFactory
  var gameboard: GameboardInterface = gameboardFactory.createGameboard(GameboardSize.Large)
  var players: (PlayerInterface, PlayerInterface) = _
  var controller = new ControllerMill(gameboardFactory.createGameboard(GameboardSize.Large))

  controller.createGameboard()


  "A Controller" when {
    "new" should {

      "have White as Starter" in {
        playerOnTurn should be(playerWhite)
      }
      "have a String representation" in {
        controller.gameboardToString should be(gameboard.toString())
      }

      "get Players" in {
        controller.getPlayerOnTurn should be(controller.playerOnTurn.toString)
        controller.getPlayerOnTurnPhase should be(PlayerGamePhase.Place.toString)
        controller.endPlayersTurn()
        controller.changePlayerOnTurn()

      }

    }
  }

  "A Controller" when {
    "running" should {
      "checkPlayers" in {
        controller.checkPlayer("White") should be(())

      }

      "perform Turns" in {
        controller.placeMan(0)
        controller.placeMan(3)
        playerTest = playerTest.changeGamePhase(PlayerGamePhase.Fly)
        controller.moveMan(0,1) should be (Error.NoError)
        controller.moveMan(0,0) should be (Error.FieldError)

        controller.flyMan(1,5) should be (Error.NoError)
        controller.flyMan(1,1) should be (Error.FieldError)
        controller.performTurn(0,1) should be (Error.NoError)

      }
    }
  }


}
