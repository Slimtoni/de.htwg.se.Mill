package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection

import scala.collection.mutable

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type ofgit the Graph-Attribute
  */
case class Gameboard[Field](vertexList: mutable.MutableList[Field], neigh: mutable.MutableList[Edge[Field]])
  extends Graph[Field] {

  //def this(size: Int) = this(new mutable.MutableList[Field], new mutable.MutableList[(Field, Field)]){}
  def vertList(): mutable.MutableList[Field] = vertexList
  def nbourList(): mutable.MutableList[Edge[Field]] = neigh

  override def addVertex(v: Field): Gameboard[Field] = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  override def addEdge(v: Field, w: Field, direc : EdgeDirection): Gameboard[Field] = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (!containsEdge(v,w) || !containsEdge(w,v)) {
      val edge = new Edge[Field](v,w,direc)
      neigh.+=(edge)
    }
    copy(vertexList, neigh)
  }

  override def containsVertex(v: Field): Boolean = {
    for (i<-this.vertexList) {
      if (v.equals(i)) return true
    }
    return false
  }

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException(v + " or "+ w + " isn`t a Vertex")
    }
    neigh.contains((v, w)) || neigh.contains((w, v))
  }
/* bis hier testen */
  override def toString: String = {
    var gameboardString: String = ""
    val it = neigh.iterator
    for (i <- it) {
      //println(i._1)
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
