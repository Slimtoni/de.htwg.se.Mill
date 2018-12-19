package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.EdgeDirection.EdgeDirection

trait Graph[V] {

  def addVertex(v: V) : Any

  def addEdge(v: V, w: V, direc: EdgeDirection) : Any

  def containsVertex(v: V) : Any

  def containsEdge(v: V, w: V) : Any
}
