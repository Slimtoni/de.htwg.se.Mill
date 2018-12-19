package de.htwg.se.NineMensMorris.a_view

import de.htwg.se.NineMensMorris.controller.{Controller, FieldChanged}

import scala.swing.Reactor

case class Tui(controller: Controller) extends Reactor {
  listenTo(controller)
  println(controller.gameboardToString)
  println("Pls enter command: ")


  def processInputLine(input: String): Unit = {

    val size = "Large"
    input match {
      case "n" => controller.createGameboard(size)
      case "c" => controller.changeFieldStatus(0, "")
      case _ => {
        //println("Pls enter fieldID and FieldStatus (Black, White, Empty)")
        var inputs =input.split(' ')
        //println(inputs(0))
        //println(inputs(1))
        controller.changeFieldStatus(inputs(0).toInt, inputs(1))
      }
    }
  }

  reactions += {
    case event: FieldChanged => println(controller.gameboardToString)
  }
}
