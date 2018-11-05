package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class GameboardFactorySpec extends WordSpec with Matchers {
  "A GameboardFactory" when { "new" should {
    val gameboardFactory = new GameboardFactory(GameboardSize.Small)
    "have a size" in {
      gameboardFactory.sz() should be(GameboardSize.Small)
    }
    val gameboard = new Gameboard[Field]()
    "create a small Gameboard" in {
      gameboardFactory.createGameboard(gameboardFactory.sz()) shouldEqual (gameboard)
    }
  }
  }

}
