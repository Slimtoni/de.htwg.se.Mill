package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import org.scalatest.{Matchers, WordSpec}

class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = new Field(0, FieldStatus.Empty)

  }}
}
