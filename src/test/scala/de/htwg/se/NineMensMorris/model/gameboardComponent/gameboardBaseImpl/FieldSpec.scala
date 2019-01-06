package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "set as Empty new Field" should {
      val emptyField = new Field(0)
      "have an id" in {
        emptyField.id should be(0)
      }
      "have a fieldStatus" in {
        emptyField.fieldStatus should be(FieldStatus.Empty)
      }
    }
    "set as new Field with Status" should {
      var newField = new Field(1, FieldStatus.White)
      "have an id" in {
        newField.id should be(1)
      }
      "have a fieldStatus" in {
        newField.fieldStatus should be(FieldStatus.White)
      }
      "be able to change FieldStatus to Black" in {
        newField = newField.changeFieldStatus(FieldStatus.Black)
        newField.fieldStatus should be(FieldStatus.Black)
      }
      "be able to change FieldStatus to White" in {
        newField = newField.changeFieldStatus(FieldStatus.White)
        newField.fieldStatus should be(FieldStatus.White)
      }
      "have a nice String representation" in {
        newField.toString should be("W")
        newField = newField.changeFieldStatus(FieldStatus.Black)
        newField.toString should be("B")
        newField = newField.changeFieldStatus(FieldStatus.Empty)
        newField.toString should be("O")
      }
    }
  }
}
