package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

class GameboardSpec extends WordSpec with Matchers {
  "A Gameboard" when { "new" should {
    val vertList = new mutable.MutableList[Field]
    val neigh = new mutable.MutableList[(Field,Field)]
    val gameboard = new Gameboard[Field](vertList, neigh)
    val field = new Field(FieldStatus.Empty)
    val field2 = new Field(FieldStatus.Black)
    "add a Vertex" in {
      val vertListTest = new mutable.MutableList[Field]()
      val gameboard_test = new Gameboard[Field](vertList)
      gameboard.addVertex(field) should equal(gameboard_test)
    }
    /*
    "not add the same Vertex" in {
      gameboard.addVertex(field) should be(false)
    }
    "add an Edge" in {
      gameboard.addEdge(field, field2) should be(true)
    }
    "not add the same Edge" in {
      gameboard.addEdge(field, field2) should be(false)
    }*/
  }
  }
}
