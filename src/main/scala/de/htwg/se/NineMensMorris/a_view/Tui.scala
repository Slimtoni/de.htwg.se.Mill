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
      case _ => {
        //var inputs =input.split(' ')
        //controller.changeFieldStatus(inputs(0).toInt, inputs(1))


      }
    }
  }

  def processGameInput() :Unit = {
    var quit = false
    while (!quit) {
      val currentPlayer = controller.playerOnTurn
      processPlayerTurn(currentPlayer)
      print("---> ")
      var input = readLine()
      var inputs = input.split(' ')
      if (inputs(0) != "quit") {

      } else {
        quit = true
      }

    }
  }
  def processPlayerTurn(currentPlayer: Player): Unit = {
    controller.checkPlayer(currentPlayer)
    //currentPlayer = controller.playerOnTurn
    //println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")
    controller.getPlayerOnTurnPhase match {
      case PlayerGamePhase.Place => {
        println("Please enter ID of the target Field to Place: ")
        val input = readInt()
        try controller.performTurn(input,0)
        catch {
          case _: Exception => println("Please enter ID of the target Field to Place: ")
        }
      }
      case PlayerGamePhase.Move => {
        println("Please enter ID of the start- and targetField to Move: ")
        val input = readLine()
        val inputs = input.split(" ")
        try controller.performTurn(inputs(0).toInt, inputs(1).toInt)
        catch {
          case _: Exception => println("Please enter ID of the start- and targetField to Move: ")
        }
      }
      case PlayerGamePhase.Fly => {
        println("Please enter ID of the start- and targetField to Fly: ")
        val input = readLine()
        val inputs = input.split(" ")
        try controller.performTurn(inputs(0).toInt, inputs(1).toInt)
        catch {
          case _: Exception => println("Please enter ID of the start- and targetField to Fly: ")
        }
      }
    }
  }

  reactions += {
    case event: FieldChanged => {
      println(controller.gameboardToString)
    }
    case _: PlayerPhaseChanged =>
      println("Player: " + controller.playerOnTurn.name + " ------ Gamephase: " + controller.playerOnTurn.phase + " Man")
    case _: GamePhaseChanged => println(controller.playerOnTurn + " lost the game!")
    //case _: CurrentPlayerChanged => controller.playerOnTurn = controller.playerOnTurn
    case _: InvalidFieldError => println("Invalid Field Error!!!")
    case _: MissingEdgeError => println("Gameboard does not contain the Edge!!!")
  }
}
