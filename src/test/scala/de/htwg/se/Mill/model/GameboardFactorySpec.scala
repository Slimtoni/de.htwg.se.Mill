package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardFactorySpec extends WordSpec with Matchers {
  "A GameboardFactory" when { "new" should {
    val gameboardFactory = new GameboardFactory(GameboardSize.Small)
    "have a size" in {
      gameboardFactory.sz() should be(GameboardSize.Small)
    }
    val gameboard = new Gameboard[Field](new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
    "create a small Gameboard" in {
      gameboardFactory.createGameboard(gameboardFactory.sz()) shouldEqual (gameboard)
    }
  }
  }

}
