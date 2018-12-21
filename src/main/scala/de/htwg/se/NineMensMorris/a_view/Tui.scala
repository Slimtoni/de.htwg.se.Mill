package de.htwg.se.NineMensMorris.a_view

import java.lang.System


import de.htwg.se.NineMensMorris.controller.impl._
import de.htwg.se.NineMensMorris.model.{Player, PlayerGamePhase}

import scala.io.StdIn.readLine
import scala.swing.Reactor

case class Tui(controller: DefaultGameController) extends Reactor {
  listenTo(controller)
  controller.createGameboard()
  var currentPlayer: Player = controller.playerOnTurn
  println(controller.gameboardToString)
  println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")


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
      print("---> ")
      var input = readLine()
      var inputs = input.split(' ')
      if (inputs(0) != "quit") {
        currentPlayer.phase match {
          case PlayerGamePhase.Place => controller.performTurn(currentPlayer,inputs(0).toInt)
          case PlayerGamePhase.Move =>
          case PlayerGamePhase.Fly => controller.performTurn(currentPlayer, inputs(0).toInt, inputs(1).toInt)
        }
      } else {
        quit = true
      }

    }
  }



  reactions += {
    case event: FieldChanged => {
      println(controller.gameboardToString)
      println("Player: " + currentPlayer.name + " ------ Gamephase: " + currentPlayer.phase + " Man")
    }
    case _: GamePhaseChanged => println(controller.playerOnTurn + " lost the game!")
    case _: CurrentPlayerChanged => currentPlayer = controller.playerOnTurn
    case _: GameOver => println(controller.playerOnTurn + " won the game!")


  }
}
