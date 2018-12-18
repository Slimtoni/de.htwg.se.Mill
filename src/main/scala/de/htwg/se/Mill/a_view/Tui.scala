package de.htwg.se.Mill.a_view

import de.htwg.se.Mill.controller.{Controller, FieldChanged}

import scala.swing.Reactor

case class Tui(controller: Controller) extends Reactor {
  listenTo(controller)
  println(controller.gameboardToString)

  def processInputLine(input: String): Unit = {

    val size = "Large"

    input match {
      case "n" => controller.createGameboard(size)
      case "c" => controller.changeFieldStatus(0, "")
      case _ => controller.gameboardToString
    }
  }

  reactions += {
    case event: FieldChanged => println(controller.gameboardToString)
  }
}
