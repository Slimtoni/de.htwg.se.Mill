package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.EdgeDirection.EdgeDirection

import scala.collection.mutable



case class Edge(source: Field, target: Field) {

  def equals(edge: Edge): Boolean = {
    val eSource: Field = edge.source
    val eTarget: Field = edge.target
    if (source.checkID(eSource) && target.checkID(eTarget)) true
    else if (source.checkID(eTarget) && target.checkID(eSource)) true
    else false
  }

  override def toString: String = source + "_______" + target
}
