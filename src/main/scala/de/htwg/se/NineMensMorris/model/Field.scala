package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus

case class Field(var id: Int, var fieldStatus: FieldStatus) {

  def changeFieldStatus(fieldStatus: FieldStatus) : Field = copy(id, fieldStatus)

  def checkID(f: Field): Boolean = {
    if(f.id == this.id) true
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