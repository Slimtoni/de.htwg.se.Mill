package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection

import scala.collection.mutable

object EdgeDirection extends Enumeration {
  type EdgeDirection = Value
  val Horizontal, Vertical = Value
}

class Edge[V](source: V, target: V, direction: EdgeDirection) extends mutable.MutableList{

  def getSource(): V = source
  def getTarget() : V = target
  def getDirection() = direction
}
