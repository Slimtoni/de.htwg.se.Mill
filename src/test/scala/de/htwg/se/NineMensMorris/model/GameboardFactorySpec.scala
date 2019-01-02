package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardFactory}
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Gameboard
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardFactorySpec extends WordSpec with Matchers {
  "A GameboardFactory" when {
    "new" should {
      val gameboardFactory = new GameboardFactory()
      val gameboard = Gameboard(new mutable.MutableList[FieldInterface], new mutable.MutableList[EdgeInterface])

    }
  }

}
