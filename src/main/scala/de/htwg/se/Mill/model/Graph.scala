package de.htwg.se.Mill.model

trait Graph[V] {

  def addVertex(v: V) : Any

  def addEdge(v: V, w: V, direc: Boolean) : Any

  def containsVertex(v: V) : Any

  def containsEdge(v: V, w: V) : Any
}
