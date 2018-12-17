package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.FieldStatus.FieldStatus

object FieldStatus extends Enumeration {
  type FieldStatus = Value
  val Empty, Black, White = Value
}

case class Field(id: Int, fieldStatus: FieldStatus) {

  def changeFieldStatus(fieldStatus: FieldStatus) : Field = copy(id, fieldStatus)

  def equals(f: Field): AnyVal = {
    if(f.id == this.id) true
  }

  override def toString: String = {
    fieldStatus match {
      case FieldStatus.Empty => "O"
      case FieldStatus.Black => "B"
      case FieldStatus.White => "W"
    }
  }
}
