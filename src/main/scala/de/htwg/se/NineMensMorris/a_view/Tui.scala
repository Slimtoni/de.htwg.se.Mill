package de.htwg.se.NineMensMorris.a_view

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.{FieldChanged, GamePhaseChanged, PlayerPhaseChanged, CaseOfMill}
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.{ControllerMill}
import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

import scala.io.StdIn.{readInt, readLine}
import scala.swing.Reactor

case class Tui(controller: ControllerMill) extends Reactor {
  listenTo(controller)
  controller.createGameboard()

  def processInputLine(input: String): Unit = {
    input match {
      case "n" => controller.createGameboard()
      case "s" => processGameInput()
      case _ =>
        //var inputs =input.split(' ')
        //controller.changeFieldStatus(inputs(0).toInt, inputs(1))
    }
  }

  def processGameInput(): Unit = {
    val quit = false
    while (!quit) {
      val currentPlayer: String = controller.getPlayerOnTurn
      try processPlayerTurn(currentPlayer)
      catch {
        case e: Exception => println("Error213: " + e)
      }
    }
  }

  def processPlayerTurn(currentPlayer: String): Unit = {
    controller.checkPlayer(currentPlayer)
    //currentPlayer = controller.playerOnTurn
    //println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")
    controller.getPlayerOnTurnPhase match {
      case "Place" =>
        var done = false
        while (!done) {
          println("Please enter ID of the target Field to Place: ")
          val input = readInt()
          val error = controller.performTurn(input, 0)
          if (error != controllerComponent.Error.NoError) println(error)
          else done = {
            println("Succesfully placed Man on the Field " + input)
            true
          }
        }
      case "Move" =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Move: ")
          val input = readLine()
          val inputs = input.split(" ")
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != controllerComponent.Error.NoError) println(error)
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
      case "Fly" =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Fly: ")
          val input = readLine()
          val inputs = input.split(" ")
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != controllerComponent.Error.NoError) println(error)
          else done = {
            if (controller.checkMill(inputs(1).toInt)) {
              processMill()
            }
            controller.endPlayersTurn()
            true
          }
        }
    }
  }

  def processMill(): Unit = {
    var done = false
    while (!done) {
      println("Player " + controller.playerOnTurn + " got a Mill. Please select a man to remove")
      val input = readInt()
      val error = controller.caseOfMill(input)
      if(error != controllerComponent.Error.NoError) println(error)
      else done = true
    }
  }

  reactions += {
    case _: FieldChanged =>
      println(controller.gameboardToString)
    case _: PlayerPhaseChanged =>
      println("Player: " + controller.playerOnTurn.name + " ------ Gamephase: " + controller.playerOnTurn.phase + " Man")
    case _: GamePhaseChanged => println(controller.playerOnTurn + " lost the game!")
    //case _: CurrentPlayerChanged => controller.playerOnTurn = controller.playerOnTurn
    case _: CaseOfMill =>
      println("Player " + controller.playerOnTurn + " got a mill. Please select a man to remove")
  }
}
