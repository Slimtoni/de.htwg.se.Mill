package de.htwg.se.Mill.model

import scala.collection.mutable

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type ofgit the Graph-Attribute
  */
case class Gameboard[Field](vertexList: mutable.MutableList[Field], neigh: mutable.MutableList[(Field, Field)])
  extends Graph[Field] {

  def vertList(): mutable.MutableList[Field] = vertexList
  def nbourList(): mutable.MutableList[(Field, Field)] = neigh

  override def addVertex(v: Field): Gameboard[Field] = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  override def addEdge(v: Field, w: Field): Gameboard[Field] = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (!containsEdge(v,w) || !containsEdge(w,v)) {
      neigh.+=((v,w))
    }
    copy(vertexList, neigh)
  }

  override def containsVertex(v: Field): Boolean = vertexList.contains(v)

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException(v + " or "+ w + " isn`t a Vertex")
    }
    neigh.contains((v, w)) || neigh.contains((w, v))
  }
}
/*
object Gameboard {
  private val _instance = new Gameboard[Field]
  def instance(): Gameboard[Field] = _instance
}*/