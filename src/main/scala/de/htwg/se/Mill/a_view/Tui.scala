package de.htwg.se.Mill.a_view

import java.util.{Observable, Observer}

import de.htwg.se.Mill.controller.Controller
import de.htwg.se.Mill.model.{Field, Gameboard}

class Tui(controller: Controller) extends Observer{

  controller.addObserver(this)

  def processInputLine(input: String): Unit = {

    val size = "Large"

    input match {
      case "n" => controller.createGameboard(size)
    }
  }

  override def update(observable: Observable, o: Any): Unit = println(controller.gameboardToString)
}
