package de.htwg.se.Mill.model

import scala.collection.mutable

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type ofgit the Graph-Attribute
  */
class Gameboard[Field](size : Int) extends Graph[Field] {

  def getSize() = size

  def getVertexList(): mutable.MutableList[Field] = vertexList
  def getNeighbourList(): mutable.MutableList[(Field, Field)] = neigh


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
    if (!neigh.contains((v,w))) {
      neigh.+=((v,w))
      true
    } else false
  }


  override def containsVertex(v: Field): Boolean = neigh.contains(v)

  override def containsEdge(v: Field, w: Field): Boolean = {
    true
  }
}
