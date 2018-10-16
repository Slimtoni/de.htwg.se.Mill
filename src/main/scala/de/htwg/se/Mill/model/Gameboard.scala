package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.Field
import sun.security.provider.certpath.AdjacencyList

import scala.collection.immutable.Stream.Empty
import scala.collection.mutable

class Gameboard[Field] extends Graph[Field] {
  // successor list of fields
  val succ = new mutable.HashMap[Field, Field]()

  override def addVertex(v: Field): Boolean = {
    if (!succ.contains(v)) {
      //succ.put(v, new Field(0, Empty))
      true
    } else false
  }

  override def addEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (containsEdge(v, w)) false
    else succ.put(v, w); true

  }

  override def containsVertex(v: Field): Boolean = succ.contains(v)

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException
    }
    succ.get(v).contains(w)
  }
}
