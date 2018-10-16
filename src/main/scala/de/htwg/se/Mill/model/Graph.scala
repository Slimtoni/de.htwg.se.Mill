package de.htwg.se.Mill.model

abstract class Graph[V] {

  def addVertex(v: V): Boolean

  def addEdge(v: V, w: V): Boolean

  def containsVertex(v: V): Boolean

  def containsEdge(v: V, w: V): Boolean
}
