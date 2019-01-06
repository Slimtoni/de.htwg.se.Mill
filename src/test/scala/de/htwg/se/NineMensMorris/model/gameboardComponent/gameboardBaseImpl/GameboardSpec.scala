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
      "adding a new Vertex in the Gameboard that already exists" in {
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
      "adding a new Edge in the Gameboard that already exists" in {
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
      /*"set an existing Field Black" in {
        var setGameboard = new Gameboard
        setGameboard = setGameboard.addEdge(new Field(0), new Field(1))

        var testGameboard1 = new Gameboard
        testGameboard1 = testGameboard1.addEdge(new Field(0, FieldStatus.Black), new Field(1))
        setGameboard.set(0, "Black") shouldEqual  Some(testGameboard1)
      }
      "set an existing Field White" in {
        var setGameboard = new Gameboard
        setGameboard = setGameboard.addEdge(new Field(0), new Field(1))

        var testGameboard2 = new Gameboard
        testGameboard2 = testGameboard2.addEdge(new Field(0, FieldStatus.White), new Field(1))
        setGameboard.set(0, "White") shouldEqual  Some(testGameboard2)
      }
      "set an existing Field Empty" in {
        var setGameboard = new Gameboard
        setGameboard = setGameboard.addEdge(new Field(0, FieldStatus.Black), new Field(1))

        var testGameboard3 = new Gameboard
        testGameboard3 = testGameboard3.addEdge(new Field(0, FieldStatus.Empty), new Field(1))
        setGameboard.set(0, "Empty") shouldEqual  Some(testGameboard3)
      }*/
      "set a non-existing Field" in {
        gameboard.set(3, "Black") shouldEqual None
      }
      "set a existing Field with wrong FieldStatus" in {
        gameboard.set(1, "wrongFieldStatus") shouldEqual None
      }
    }
  }
}

