package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class EdgeSpec extends WordSpec with Matchers {
  "A Edge" when { "new" should {
    val edge = new Edge(1, 1)
    "have a src and a target"  in {
      edge.src() should be(1)
      edge.tar() should be(1)
    }
    "have a nice String representation" in {
      edge.toString should be("src:1 - tar:1")
    }
  }}
}