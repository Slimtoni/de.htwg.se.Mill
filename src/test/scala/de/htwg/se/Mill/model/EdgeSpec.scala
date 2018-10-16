package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class EdgeSpec extends WordSpec with Matchers {
  "A Edge" when { "new" should {
    val edge = new Edge(1, 2)
    "has a src"  in {
      edge.src() should be(1)
    }
    "has a target" in {
      edge.tar() should be(2)
    }
    "have a nice String representation" in {
      edge.toString should be("src:1 - tar:2")
    }
  }}
}