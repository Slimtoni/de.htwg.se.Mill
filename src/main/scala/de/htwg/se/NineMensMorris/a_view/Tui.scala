package de.htwg.se.NineMensMorris.a_view

import de.htwg.se.NineMensMorris.controller.impl._
import de.htwg.se.NineMensMorris.model.{Player, PlayerGamePhase}

import scala.io.StdIn.{readInt, readLine}
import scala.swing.Reactor

case class Tui(controller: DefaultGameController) extends Reactor {
  listenTo(controller)
  controller.createGameboard()
  //println(controller.gameboardToString)
  //println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")

  def processInputLine(input: String): Unit = {
    input match {
      case "n" => controller.createGameboard()
      case "s" => processGameInput()
      case _ =>
        //var inputs =input.split(' ')
        //controller.changeFieldStatus(inputs(0).toInt, inputs(1))
    }
  }

  def processGameInput() :Unit = {
    var quit = false
    while (!quit) {
      val currentPlayer = controller.playerOnTurn
      try processPlayerTurn(currentPlayer)
      catch {
        case e: Exception => println("Error: " + e)
      }
    }
  }
  def processPlayerTurn(currentPlayer: Player): Unit = {
    controller.checkPlayer(currentPlayer)
    //currentPlayer = controller.playerOnTurn
    //println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")
    controller.getPlayerOnTurnPhase match {
      case PlayerGamePhase.Place =>
        var done = false
        while (!done) {
          println("Please enter ID of the target Field to Place: ")
          val input = readInt()
          val error = controller.performTurn(input, 0)
          if (error != Error.NoError) println(error)
          else done = {
            println("Succesfully placed Man on the Field " + input)
            true
          }
        }
      case PlayerGamePhase.Move =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Move: ")
          val input = readLine()
          val inputs = input.split(" ")
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != Error.NoError) println(error)
          else done = {
            println("Succesfully moved Man from Field " + inputs(0) + " to Field " + inputs(1))
            true
          }
        }
      case PlayerGamePhase.Fly =>
        var done = false
        while (!done) {
          println("Please enter ID of the start- and targetField to Fly: ")
          val input = readLine()
          val inputs = input.split(" ")
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != Error.NoError) println(error)
          else done = true
        }
    }
  }

  reactions += {
    case _: FieldChanged => {
      println(controller.gameboardToString)
    }
    case _: PlayerPhaseChanged =>
      println("Player: " + controller.playerOnTurn.name + " ------ Gamephase: " + controller.playerOnTurn.phase + " Man")
    case _: GamePhaseChanged => println(controller.playerOnTurn + " lost the game!")
    //case _: CurrentPlayerChanged => controller.playerOnTurn = controller.playerOnTurn
  }
}
