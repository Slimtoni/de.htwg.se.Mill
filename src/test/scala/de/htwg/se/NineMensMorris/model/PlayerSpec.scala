package de.htwg.se.NineMensMorris.model

import org.scalatest.{Matchers, WordSpec}


class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name", PlayerGamePhase.Place, 0)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}
}
