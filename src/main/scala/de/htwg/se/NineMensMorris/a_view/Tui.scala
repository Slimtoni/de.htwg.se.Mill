package de.htwg.se.NineMensMorris.a_view

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.Error._
import de.htwg.se.NineMensMorris.controller.controllerComponent.{FieldChanged, GamePhaseChanged, PlayerPhaseChanged, CaseOfMill}
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.ControllerMill


import scala.io.StdIn.{readInt, readLine}
import scala.swing.Reactor

case class Tui(controller: ControllerMill) extends Reactor {
  listenTo(controller)
  controller.createGameboard()

  def processInputLine(): Unit = {
    println("Nine mens morris by Toni & Matze\n" +
      "--------------------------------\n" +
      "s - start game\n" +
      "q - quit\n" +
      "--------------------------------")
    val input = readLine()
    input match {
      case "s" => processGameInput()
      case "q" => sys.exit()
      case _ => processInputLine()
    }
  }

  def processGameInput(): Unit = {
    val quit = false
    while (!quit) {
      val currentPlayer: String = controller.getPlayerOnTurn
      try processPlayerTurn(currentPlayer)
      catch {
        case e: Exception => println("Error: " + e)
      }
    }
  }

  def endGame(): Unit = {
    println("If you want to exit the game press y. Otherwise press any button")
    val input = readLine()
    if (input.equals("y")) {
      sys.exit()
    }
  }

  def processPlayerTurn(currentPlayer: String): Unit = {
    controller.checkPlayer(currentPlayer)
    controller.getPlayerOnTurnPhase match {
      case "Place" =>
        var done = false
        while (!done) {
          println("Please enter ID of the target Field to Place: ")
          try {
            val input = readLine()
            if (input.equals("q")) {
              endGame()
            } else {
              val inputs = input.split(" ")
              val error = controller.performTurn(inputs(0).toInt, 0)
              if (error != controllerComponent.Error.NoError) errorMessage(error)
              else done = {
                println("Succesfully placed Man on the Field " + input)
                if (controller.checkMill(inputs(0).toInt)) {
                  processMill()
                }
                controller.endPlayersTurn()
                true
              }
            }
          }
          catch {
            case ioobe: IndexOutOfBoundsException => errorMessage(InputError)
            case nfe: NumberFormatException => errorMessage(InputError)

          }
        }
      case "Move" =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Move: ")
          val input = readLine()
          val inputs = input.split(" ")
          if (input.equals("q")) {
            endGame()
          } else {
            try {
              val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
              if (error != controllerComponent.Error.NoError) errorMessage(error)
              else done = {
                println("Succesfully moved Man from Field " + inputs(0) + " to Field " + inputs(1))
                println("Succesfully placed Man on the Field " + input)
                if (controller.checkMill(inputs(1).toInt)) {
                  processMill()
                }
                controller.endPlayersTurn()
                true
              }
            }

            catch {
              case ioobe: IndexOutOfBoundsException => errorMessage(InputError)
              case nfe: NumberFormatException => errorMessage(InputError)
            }
          }
        }
      case "Fly" =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Fly: ")
          val input = readLine()
          val inputs = input.split(" ")
          if (input.equals("q")) {
            endGame()
          } else {
            try {
              val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
              if (error != controllerComponent.Error.NoError) errorMessage(error)
              else done = {
                if (controller.checkMill(inputs(1).toInt)) {
                  processMill()
                }
                controller.endPlayersTurn()
                true
              }
            }

            catch {
              case ioobe: IndexOutOfBoundsException => errorMessage(InputError)
              case nfe: NumberFormatException => errorMessage(InputError)
            }
          }
        }
    }
  }

  def processMill(): Unit = {
    var done = false
    while (!done) {
      println("Player " + controller.playerOnTurn + " got a Mill. Please select a man to remove")
      try {
        val input = readInt()
        val error = controller.caseOfMill(input)
        if (error != controllerComponent.Error.NoError) errorMessage(error)
        else done = true
      } catch {
        case nfe: NumberFormatException => errorMessage(InputError)
      }
    }
  }

  reactions += {
    case _: FieldChanged =>
      println(controller.gameboardToString)
    case _: PlayerPhaseChanged =>
      println("Player: " + controller.playerOnTurn.name + " ------ Gamephase: " + controller.playerOnTurn.phase + " Man")
    case _: GamePhaseChanged => println(controller.playerOnTurn + " lost the game!")
    case _: CaseOfMill =>
      println("Player " + controller.playerOnTurn + " got a mill. Please select a man to remove")
  }
}
