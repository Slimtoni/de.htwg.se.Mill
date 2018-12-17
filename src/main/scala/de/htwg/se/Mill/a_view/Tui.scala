package de.htwg.se.Mill.a_view

import java.util.{Observable, Observer}

import de.htwg.se.Mill.controller.Controller
import de.htwg.se.Mill.model.{Field, Gameboard}

class Tui(controller: Controller) extends Observer{


  def processInputLine(input: String, gameboard: Gameboard[Field]) = {

    val size = "Large"

    input match {
      case "n" => controller.createGameboard(size)
    }
  }

  override def update(observable: Observable, o: Any): Unit = println(controller.gameboardToString)
}
