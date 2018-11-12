package de.htwg.se.Mill.model

import scala.collection.mutable

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type ofgit the Graph-Attribute
  */
class Gameboard[Field] extends Graph[Field] {

  def vertList(): mutable.MutableList[Field] = vertexList
  def nbourList(): mutable.MutableList[(Field, Field)] = neigh
  // neighbour list of fields
  val neigh = new mutable.MutableList[(Field, Field)]
  val vertexList = new mutable.MutableList[Field]

  override def addVertex(v: Field): Boolean = {
    if (!vertexList.contains(v)) {
      vertexList.+=(v)
      true
    } else false
  }

  override def addEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (containsEdge(v,w) || containsEdge(w,v)) {
      neigh.+=((v,w))
      false
    } else {
      neigh.+=((v,w))
      true
    }
  }

  override def containsVertex(v: Field): Boolean = vertexList.contains(v)

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException(v + " or "+ w + " isn`t a Vertex")
    }
    neigh.contains((v, w)) || neigh.contains((w, v))
  }
  
  override def toString: String = {
    var gameboardString: String = ""
    val it = neigh.iterator
    for (i <- it) {
      if (i.getDirection() == EdgeDirection.Horizontal) {
        gameboardString += i.getSource()
        gameboardString += i.toString
        gameboardString += i.getTarget() + "\n"
      }
      else {
        if (it.hasNext) gameboardString += i.toString + "\n"
        else gameboardString += "\n"
      }
    }
    gameboardString
  }
}
