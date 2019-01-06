package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardMockImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardInterface}

import scala.annotation.meta.field
import scala.collection.mutable

class Gameboard() extends GameboardInterface {
  def getField(id: Int): FieldInterface = EmptyField

  override def vertexList: mutable.MutableList[FieldInterface] = new mutable.MutableList[FieldInterface]

  override def neigh: mutable.MutableList[EdgeInterface] = new mutable.MutableList[EdgeInterface]

  override def addVertex(v: FieldInterface): GameboardInterface = this

  override def addEdge(v: FieldInterface, w: FieldInterface): GameboardInterface = this

  override def containsVertex(v: FieldInterface): Boolean = false

  override def containsEdge(v: FieldInterface, w: FieldInterface): Boolean = false

  override def set(field: Int, fieldStatus: String): Option[GameboardInterface] = Some(this)
}

object EmptyField extends FieldInterface {
  override def id: Int = 0

  override def fieldStatus: FieldStatus = FieldStatus.Empty

  override def millneigh: List[(FieldInterface, FieldInterface)] = List.empty

  override def changeFieldStatus(fieldStatus: FieldStatus): FieldInterface = this

  override def checkID(f: FieldInterface): Boolean = true




}