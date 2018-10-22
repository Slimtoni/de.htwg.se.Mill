package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.FieldStatus.FieldStatus

object FieldStatus extends Enumeration {
  type FieldStatus = Value
  val Empty, Black, White = Value
}

class Field(fieldStatus: FieldStatus) {

  def getfieldStatus() = fieldStatus

  override def toString: String = "Fieldstatus is: " + fieldStatus.toString
}
