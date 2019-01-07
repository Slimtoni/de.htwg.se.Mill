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
    "be able to check the placed men" in {
      player.checkedPlacedMen() shouldEqual Some(Player("White", PlayerGamePhase.Place, 0))
    }
    "be able to check the placed men and change to Move GamePhase" in {
      player.numberPlacedMen = 9
      player.checkedPlacedMen() shouldEqual Some(Player("White", PlayerGamePhase.Move, 9))
    }
    "be able to check the placed men and change to Fly GamePhase" in {
      player.numberPlacedMen = 9
      player.numberLostMen = 6
      player.checkedPlacedMen() shouldEqual Some(Player("White", PlayerGamePhase.Fly, 9))
    }
    "be able to check the placed men and loose the game" in {
      player.numberPlacedMen = 9
      player.numberLostMen = 7
      player.checkedPlacedMen() shouldEqual None
    }
    "be able to change the GamePhase" in {
      player.changeGamePhase(PlayerGamePhase.Place) shouldEqual Player("White", PlayerGamePhase.Place, 9)
    }
    "be able to increment the Placed Men" in {
      var player1 = Player("White", PlayerGamePhase.Move, 9)
      player1.incrementPlacedMen() shouldEqual Player("White", PlayerGamePhase.Move, 10)
    }
    "be able to decrement the Placed Men" in {
      var player1 = Player("White", PlayerGamePhase.Move, 9)
      player1.decrementPlacedMen() shouldEqual Player("White", PlayerGamePhase.Move, 8)
    }
    "be able to compare the equality with other Player" in {
      player.equals(Player("White", PlayerGamePhase.Place,0)) should be(true)
    }
  }}
}
