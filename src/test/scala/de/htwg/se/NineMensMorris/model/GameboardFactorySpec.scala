package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardFactory
import org.scalatest.{Matchers, WordSpec}

class GameboardFactorySpec extends WordSpec with Matchers {
  "A GameboardFactory" when {
    "new" should {
      val gameboardFactory = new GameboardFactory()
      "create new Gameboard with 24 Fields" in {

        val gameboard = gameboardFactory.createGameboard(GameboardSize.Nine)
        gameboard.toString should be(
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
      "create new Gameboard with 16 Fields" in {
        val gameboard = gameboardFactory.createGameboard(GameboardSize.Six)
        gameboard.toString should be("")
      }
    }

  }

}
