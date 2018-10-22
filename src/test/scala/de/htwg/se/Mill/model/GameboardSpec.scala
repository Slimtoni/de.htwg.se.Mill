package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class GameboardSpec extends WordSpec with Matchers {
  "A Gameboard" when { "new" should {
    val gameboard = new Gameboard[Field](8)
    "have a size" in {
      gameboard.sz() should be(8)
    }
    val field = new Field(FieldStatus.Empty)
    val field2 = new Field(FieldStatus.Black)
    "be able to add a Vertex" in {
      gameboard.addVertex(field) should be(true)
    }
    "not be able to add the same Vertex" in {
      gameboard.addVertex(field) should be(false)
    }
    "be able to add an Edge" in {
      gameboard.addEdge(field, field2) should be(true)
    }
    "not be able to add the same Edge" in {
      gameboard.addEdge(field, field2) should be(false)
    }
  }

  }
}
