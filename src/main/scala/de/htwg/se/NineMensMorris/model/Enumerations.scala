package de.htwg.se.NineMensMorris.model


object Status extends Enumeration {
  type FieldStatus = Value
  val Empty, Black, White = Value
}

object FieldStatus extends Enumeration {
  type FieldStatus = Value
  val Empty, Black, White = Value
}


object PlayerGamePhase extends Enumeration {
  type PlayerGamePhase = Value
  val Place, Move, Fly = Value
}