package de.htwg.se.NineMensMorris.controller.controllerComponent

object Error extends Enumeration {
  type Error = Value
  val NoError, FieldError, EdgeError, SelectError = Value

  val map = Map[Error, String] (
    NoError -> "No Error occured!",
    FieldError -> "A Field Error occured!",
    EdgeError -> "An Edge Error occured!",
    SelectError -> "Please Select a man from your opponent")

  def errorMessage(error: Error): String = map(error)
}
