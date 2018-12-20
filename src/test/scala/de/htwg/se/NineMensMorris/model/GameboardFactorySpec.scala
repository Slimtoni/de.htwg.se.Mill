package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.EdgeDirection.EdgeDirection
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardFactorySpec extends WordSpec with Matchers {
  "A GameboardFactory" when { "new" should {
    val gameboardFactory = new GameboardFactory()

    val gameboard = Gameboard(new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])

  }
  }

}
