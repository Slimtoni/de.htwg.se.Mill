package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface


 case class Field(var id: Int, var fieldStatus: FieldStatus, var millneigh: List[(Field, Field)]) extends FieldInterface {

  def changeFieldStatus(fieldStatus: FieldStatus) : Field = copy(id, fieldStatus)

  def checkID(f: FieldInterface): Boolean = {
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

   override def neighList: List[(Field, Field)] = ???
 }
