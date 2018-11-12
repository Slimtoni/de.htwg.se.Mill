package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardSpec extends WordSpec with Matchers {
  "A Gameboard" when {
    "new" should {
      val gameboard = new Gameboard[Field](new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
      val field = new Field(0, FieldStatus.Empty)
      val field2 = new Field(0, FieldStatus.Empty)
      val field3 = new Field(9, FieldStatus.Empty)
      val thrown = the [IllegalArgumentException] thrownBy gameboard.containsEdge(field3, field2)

      "have a Vertex List" in {
        gameboard.vertList() should be(mutable.MutableList())
      }
      "have a nachbar List" in {
        gameboard.nbourList() should be(mutable.MutableList())
      }
      "add a vertex" in {
        gameboard.addVertex(field) should equal(gameboard)
      }
      "add an edge" in {
        gameboard.addEdge(field, field2, EdgeDirection.Vertical) should equal(gameboard)
      }
      "should not have 2 equal vertex" in {
        gameboard.containsVertex(field2) should equal(true)
      }
      "should not have 2 equal edges" in {
        gameboard.containsEdge(field, field2) should equal(false)
      }




    }
  }
}

