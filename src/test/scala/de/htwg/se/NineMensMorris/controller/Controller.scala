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
        controller.gameboardToString should be(
          "O__________O__________O\n" +
            "|          |          |\n" +
            "|   O______O______O   |\n" +
            "|   |      |      |   |\n" +
            "|   |   O__O__O   |   |\n" +
            "|   |   |     |   |   |\n" +
            "O___O___O     O___O___O\n" +
            "|   |   |     |   |   |\n" +
            "|   |   O__O__O   |   |\n" +
            "|   |      |      |   |\n" +
            "|   O______O______O   |\n" +
            "|          |          |\n" +
            "O__________O__________O\n")
      }

      "get Players" in {
        controller.getPlayerOnTurn should be(controller.playerOnTurn.toString)

        controller.getPlayerOnTurnPhase should be(PlayerGamePhase.Place.toString)


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
        controller.placeMan(0) should be(Error.FieldError)
        playerTest = playerTest.changeGamePhase(PlayerGamePhase.Fly)
        controller.moveMan(0, 1) should be(Error.NoError)
        controller.moveMan(0, 0) should be(Error.FieldError)

        controller.flyMan(1, 5) should be(Error.NoError)
        controller.flyMan(1, 1) should be(Error.FieldError)
        controller.performTurn(0, 1) should be(Error.NoError)


      }

      "change Player on Turn" in {
        playerOnTurn = playerWhite

        controller.changePlayerOnTurn()

        playerOnTurn should be(playerBlack)

        controller.endPlayersTurn() should be(())

        playerOnTurn should be(playerWhite)
      }
    }
  }


}
