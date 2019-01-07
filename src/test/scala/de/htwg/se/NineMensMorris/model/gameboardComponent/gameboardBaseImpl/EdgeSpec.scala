package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import org.scalatest.{Matchers, WordSpec}

class EdgeSpec extends WordSpec with Matchers {
  "An Edge" when {
    "set with Empty Field" should {
      val edge = Edge(new Field(0), new Field(1))
      "have an empty source Field" in {
        edge.source should equal(new Field(0))
      }
      "have an empty target Field" in {
        edge.target should equal(new Field(1))
      }
      "be equals with other Edge" in {
        val equalEdge = Edge(new Field(0), new Field(1))
        edge.equals(equalEdge) should be(true)
      }
      "be equals with turned Edge" in {
        val turnedEdge = Edge(new Field(1), new Field(0))
        edge.equals(turnedEdge) should be(true)
      }
      "not be equals with other Edge" in {
        val diffrentEdge = Edge(new Field(5),new Field(3))
        edge.equals(diffrentEdge) should be(false)
      }
      "have a nice String representation" in {
        edge.toString should be("O_______O")
      }
    }
  }
}
