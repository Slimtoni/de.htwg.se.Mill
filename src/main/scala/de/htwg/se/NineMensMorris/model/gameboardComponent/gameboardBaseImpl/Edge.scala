package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface}

case class Edge(source: FieldInterface, target: FieldInterface) extends EdgeInterface {

  def equals(edge: EdgeInterface): Boolean = {
    val eSource: FieldInterface = edge.source
    val eTarget: FieldInterface = edge.target
    if (source.checkID(eSource) && target.checkID(eTarget)) true
    else if (source.checkID(eTarget) && target.checkID(eSource)) true
    else false
  }

  override def toString: String = source + "_______" + target
}
