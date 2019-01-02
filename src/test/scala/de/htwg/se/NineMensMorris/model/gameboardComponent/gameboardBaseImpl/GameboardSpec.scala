package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardInterface}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardSpec extends WordSpec with Matchers {
  "A Gameboard is the playingfield of Nine Mens Morris. A Gameboard" when {
    "to be constructed empty" should {
      var gameboard = new Gameboard()
      "vertexList should be empty" in {
        gameboard.vertexList should be(mutable.MutableList())
      }
      "neighbourList should be empty" in {
        gameboard.neigh should be (mutable.MutableList())
      }
      "adding a new Vertex in the Gameboard" in {
        val field1 = new Field(1)
        gameboard = gameboard.addVertex(field1)
        gameboard.vertexList should be(mutable.MutableList(field1))
      }
      "adding a new Edge in the Gameboard" in {
        val field1 = new Field(1)
        val field2 = new Field(2)
        val edge = Edge(field1, field2)
        gameboard = gameboard.addEdge(field1, field2)
        gameboard.vertexList should be(mutable.MutableList(field1, field2))
        gameboard.neigh should be(mutable.MutableList(edge))
      }
      "get a Field with ID" in {
        val getField = gameboard.getField(1)
        getField shouldEqual new Field(1)
      }
      "get a Field that not exist" in {
        val getMissingField = gameboard.getField(30)
        getMissingField shouldEqual new Field(99)
      }
      "contain a Vertex" in {
        gameboard.containsVertex(new Field(1)) should be(true)
      }
      "not containing a Vertex" in {
        gameboard.containsVertex(new Field(30)) should be(false)
      }
      "contain an Edge" in {
        gameboard.containsEdge(new Field(1), new Field(2)) should be(true)
      }
      "not containing an Edge" in {
        gameboard.containsEdge(new Field(3), new Field(1)) should be(false)
      }
      "set an existing Field" in {
        val field1 = Field(1, FieldStatus.Black)
        val field2 = Field(2, FieldStatus.Empty)
        var testGameboard = new Gameboard
        testGameboard = testGameboard.addEdge(field1,field2)
        //gameboard.set(1, "Black") shouldEqual  Some(testGameboard)
      }
      "set a non-existing Field" in {
        gameboard.set(3, "Black") shouldEqual None
      }
      "set a existing Field with wrong FieldStatus" in {
        gameboard.set(1, "wrongFieldStatus") shouldEqual None
      }
    }
  }
}

