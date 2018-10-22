package de.htwg.se.Mill.model

import scala.collection.mutable

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type of the Graph-Attribute
  */
class Gameboard[Field](size : Int) extends Graph[Field] {

  // neighbour list of fields
  val neigh = new mutable.HashMap[Field, mutable.HashMap[Field, Int]]()
  val vertexList = new mutable.HashMap[Int, Field]()

  override def addVertex(v: Field): Boolean = {
    if (!neigh.contains(v)) {
      neigh.put(v, mutable.HashMap())
      true
    } else false
  }

  override def addEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (containsEdge(v, w)) false
    else {
      //val neigh.get(v)
      true
    }

  }

  override def containsVertex(v: Field): Boolean = neigh.contains(v)

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException
    }
    neigh.get(v).contains(w)
  }
}
