package de.htwg.se.Mill.model

import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = new Field(FieldStatus.Empty)
    "have a FieldStatus" in {
      field.getfieldStatus() should be(FieldStatus.Empty)
    }
  }}
}
