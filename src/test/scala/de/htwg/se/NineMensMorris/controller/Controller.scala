package de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.ControllerMill
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardFactory
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player


class Controller extends WordSpec with Matchers {
  var playerWhite: PlayerInterface = _
  var playerBlack: PlayerInterface = _
  var playerTest: PlayerInterface = new Player("White", PlayerGamePhase.Place, 0, 0)
  var playerOnTurn: PlayerInterface = _
  var gameboardFactory = new GameboardFactory
  var gameboard = gameboardFactory.createGameboard(GameboardSize.Nine)
  var players: (PlayerInterface, PlayerInterface) = _
  var controller = new ControllerMill(gameboardFactory.createGameboard(GameboardSize.Nine))
  gameboard.getField(7).changeFieldStatus(FieldStatus.Black)

  controller.createGameboard()
  val v = controller.gameboard.vertexList


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

        controller.placeMan(8)
        controller.placeMan(9)
        controller.placeMan(22)

        controller.placeMan(8) should be(Error.FieldError)
        playerTest = playerTest.changeGamePhase(PlayerGamePhase.Fly)
        controller.moveMan(9, 10) should be(Error.NoError)
        controller.flyMan(10, 12) should be(Error.NoError)
        controller.moveMan(9, 10) should be(Error.FieldError)
        controller.flyMan(9, 10) should be(Error.FieldError)

        controller.performTurn(22, 23) should be(Error.FieldError)


        true should be(true)
      }

      "change Player on Turn" in {
        playerOnTurn = playerWhite

        controller.changePlayerOnTurn()

        playerOnTurn should be(playerBlack)

        controller.endPlayersTurn() should be(())

        playerOnTurn should be(playerWhite)
      }

      "check for Mills" in {

        controller.changeFieldStatus(0, "Black")
        controller.changeFieldStatus(1, "Black")
        controller.changeFieldStatus(2, "Black")
        controller.changeFieldStatus(6, "Black")
        controller.changeFieldStatus(7, "Black")

        controller.changeFieldStatus(21, "White")
        controller.changeFieldStatus(22, "White")
        controller.changeFieldStatus(23, "White")
        controller.changeFieldStatus(9, "White")


        controller.checkMill(0) should be(true)
        controller.changePlayerOnTurn()
        controller.checkMill(21) should be(true)

        controller.caseOfMill(9) should be (Error.NoError)
        controller.caseOfMill(0) should be (Error.SelectError)

        controller.killMan(6)
        v(6).fieldStatus should be(FieldStatus.Empty)
        controller.changePlayerOnTurn()
        controller.caseOfMill(7) should be (Error.NoError)



      }
    }
  }
}
