package de.htwg.se.Mill.a_view

import de.htwg.se.Mill.controller.{Controller, FieldChanged}

import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {
  listenTo(controller)

  def processInputLine(input: String): Unit = {

    val size = "Large"

    input match {
      case "n" => controller.createGameboard(size)
      case _ => controller.gameboardToString
    }
  }

  reactions += {
    case event: FieldChanged => println(controller.gameboardToString)
  }

}
