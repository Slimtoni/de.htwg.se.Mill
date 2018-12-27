package de.htwg.se.NineMensMorris.controller.impl

object Error extends Enumeration {
  type Error = Value
  val NoError, FieldError, EdgeError = Value
}