package de.htwg.se.NineMensMorris.controller.controllerComponent

object Error extends Enumeration {
  type Error = Value
  val NoError, FieldError, EdgeError, SelectError, InputError, LoadError, SaveError, KillManError = Value

  val map: Map[Error, String] = Map[Error, String](
    NoError -> "No Error: No Error occured!",
    FieldError -> "Error: Please select a valid field!",
    EdgeError -> "Error: Please select two connected Fields!",
    SelectError -> "Error: Please Select a valid man",
    InputError -> "Error: Please use the correct input format",
    LoadError -> "There was an error loading the file",
    SaveError -> "There was an error saving the file",
    KillManError -> "All Field are catched in a Mill")


  def errorMessage(error: Error): String = map(error)
}
