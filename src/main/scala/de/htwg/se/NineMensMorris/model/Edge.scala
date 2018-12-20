package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.EdgeDirection.EdgeDirection

import scala.collection.mutable

object EdgeDirection extends Enumeration {
  type EdgeDirection = Value
  val Horizontal, Vertical = Value
}

case class Edge[V](source: V, target: V, direction: EdgeDirection) extends mutable.MutableList{

  def getSource(): V = source
  def getTarget() : V = target
  def getDirection() = direction
}
