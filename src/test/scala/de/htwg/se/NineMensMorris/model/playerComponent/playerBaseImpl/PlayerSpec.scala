package de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl
import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import org.scalatest.{Matchers, WordSpec}


class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("White", PlayerGamePhase.Place, 0)
    "have a name"  in {
      player.name should be("White")
    }
    "have a gamephase" in {
      player.phase should be(PlayerGamePhase.Place)
    }
    "have a number of placed men" in {
      player.numberPlacedMen should be(0)
    }
    "have a nice String representation" in {
      player.toString should be("White")
    }
  }}
}
