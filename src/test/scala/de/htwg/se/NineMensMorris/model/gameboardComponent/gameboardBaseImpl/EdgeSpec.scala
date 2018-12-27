package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.{Field, FieldStatus}
import org.scalatest.{Matchers, WordSpec}

class EdgeSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = Field(0, FieldStatus.Empty)
  }}
}
