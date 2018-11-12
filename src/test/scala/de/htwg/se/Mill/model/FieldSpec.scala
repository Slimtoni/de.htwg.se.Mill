package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "A Field" when {
    "new" should {
      var field = new Field(0, FieldStatus.Empty)
      var field2 = new Field(1, FieldStatus.Empty)
      "have a FieldStatus" in {
        field.getfieldStatus() should be(FieldStatus.Empty)
      }

      "and is a field" in {
        field should not be (null)
      }
      "should have a unique id" in {
        field.equals(field2) should not be (true)
      }

      "new Status" in {
        field = field.changeFieldStatus(FieldStatus.White)
        field.getfieldStatus() should be(FieldStatus.White)

      }
      "a nice String representation" in {
        field.toString should be("W")
      }

    }


  }

}
