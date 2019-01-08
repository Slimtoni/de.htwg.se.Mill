package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface

import scala.collection.mutable


case class Field(var id: Int, var fieldStatus: FieldStatus, var millneigh: mutable.MutableList[(FieldInterface, FieldInterface)]) extends FieldInterface {

  def changeFieldStatus(fieldStatus: FieldStatus): Field = copy(id, fieldStatus)

  def changeMillNeigh(list: mutable.MutableList[(FieldInterface, FieldInterface)]): Field = copy(id, fieldStatus, list)

  def checkID(f: FieldInterface): Boolean = {
    if (f.id == this.id) true
    else false
  }


  override def toString: String = {
    fieldStatus match {
      case FieldStatus.Empty => "O"
      case FieldStatus.Black => "B"
      case FieldStatus.White => "W"
    }
  }

}
