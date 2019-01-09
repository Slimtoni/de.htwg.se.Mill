package de.htwg.se.NineMensMorris.controller.controllerComponent

object Error extends Enumeration {
  type Error = Value
  val NoError, FieldError, EdgeError, SelectError, InputError = Value

  val map = Map[Error, String] (
    NoError -> "No Error: No Error occured!",
    FieldError -> "Error: Please select a valid field!",
    EdgeError -> "Error: Please select two connected Fields!",
    SelectError -> "Error: Please Select a valid man",
    InputError -> "Error: Please use the correct input format")


  def errorMessage(error: Error): Unit = println(map(error))
}
