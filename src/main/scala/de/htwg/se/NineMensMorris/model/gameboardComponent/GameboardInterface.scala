package de.htwg.se.NineMensMorris.model.gameboardComponent

import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus

import scala.collection.mutable

trait GameboardInterface {
  def vertexList: mutable.MutableList[FieldInterface]
  def neigh: mutable.MutableList[EdgeInterface]

  def getField(id: Int): FieldInterface
  def addVertex(v: FieldInterface): GameboardInterface
  def addEdge(v: FieldInterface, w: FieldInterface): GameboardInterface
  def containsVertex(v: FieldInterface): Boolean
  def containsEdge(v: FieldInterface, w: FieldInterface): Boolean
  def set(field: Int, fieldStatus: String): Option[GameboardInterface]
}

trait EdgeInterface {
  def source: FieldInterface
  def target: FieldInterface

  def equals(edge: EdgeInterface): Boolean
}

trait FieldInterface {
  def id: Int
  def fieldStatus: FieldStatus
  def neighList: List[(_, _)]

  def changeFieldStatus(fieldStatus: FieldStatus): FieldInterface
  def checkID(f: FieldInterface): Boolean
}