package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

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
    }
  }
}

