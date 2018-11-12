package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class EdgeSpec extends WordSpec with Matchers {
  "An Edge" when {
    "new" should {
      val edge = new Edge(0, 1, EdgeDirection.Horizontal)
      val edge2 = new Edge(0, 1, EdgeDirection.Vertical)
      "have a Source" in {
        edge.getSource() should be (0)
      }
      "have a target" in {
        edge.getTarget() should be (1)
      }
      "have a direction" in {
        edge.getDirection() should be (EdgeDirection.Horizontal)
      }

      "have a nice string representation" in {
        edge.toString() should be ("__")
        edge2.toString() should be ("|  |")
      }
    }
  }
}
