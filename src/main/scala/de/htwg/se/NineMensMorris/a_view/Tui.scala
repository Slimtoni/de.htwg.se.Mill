package de.htwg.se.NineMensMorris.a_view

import com.typesafe.scalalogging.LazyLogging
import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.Error._
import de.htwg.se.NineMensMorris.controller.controllerComponent._

import scala.io.StdIn.{readInt, readLine}
import scala.swing.Reactor
import org.apache.logging.log4j.{ LogManager, Logger }

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)
  var gamestarted = false
  val LOG: Logger = LogManager.getLogger(this.getClass)

  def processInputLine(input: String): Unit = {
    input match {
      case "n" =>
        controller.startNewGame()
        gamestarted = true
      case "q" => System.exit(0)
      case "s" => val err = controller.save("mill.xml")
        err match {
          case Error.NoError => LOG.info("Game saved successfully")
          case Error.SaveError => LOG.error(errorMessage(SaveError))
        }
      case "l" =>

        val err = controller.load("mill.xml")
        err match {
          case Error.NoError => gamestarted = true
          case Error.LoadError => LOG.error(errorMessage(err))

        }


      case "g" =>
        if (gamestarted) processPlayerTurn()
        else println("Please start a new Game!")
      case _ =>
    }
  }

  def endGame(): Unit = {
    println("If you want to exit the game press y. Otherwise press any button")
    val input = readLine()
    if (input.equals("y")) {
      gamestarted = false
      sys.exit()
    }
  }

  def processPlayerTurn(): Unit = {
    val currentPlayer = controller.getPlayerOnTurn
    controller.getPlayerOnTurnPhase match {
      case "Place" =>
        println("Please enter ID of the target Field to Place: ")
        try {
          val dummyField = 99
          val input = readLine().toInt
          val error = controller.performTurn(input, dummyField)
          if (error != controllerComponent.Error.NoError) {
            LOG.error(errorMessage(error))
            processPlayerTurn()
          }
          else {
            println("Succesfully placed Man on the Field " + input)
            if (controller.checkMill(input)) {
              processMill()
            }
            controller.endPlayersTurn()
          }
        }
        catch {
          case ioobe: IndexOutOfBoundsException => errorMessage(InputError)
          case nfe: NumberFormatException => errorMessage(InputError)
        }

      case "Move" =>
        println("Please enter ID of the start- and targetField to Move: ")
        val input = readLine()
        val inputs = input.split(" ")
        try {
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != controllerComponent.Error.NoError) {
            LOG.error(errorMessage(error))
            processPlayerTurn()
          }
          else {
            println("Succesfully moved Man from Field " + inputs(0) + " to Field " + inputs(1))
            LOG.info("Succesfully placed Man on the Field " + input)
            if (controller.checkMill(inputs(1).toInt)) {
              processMill()
            }
            controller.endPlayersTurn()
          }
        }

        catch {
          case ioobe: IndexOutOfBoundsException => errorMessage(InputError)
          case nfe: NumberFormatException => errorMessage(InputError)
        }

      case "Fly" =>
        println("Please enter ID of the start- and targetField to Fly: ")
        val input = readLine()
        val inputs = input.split(" ")
        try {
          val error = controller.performTurn(inputs(0).toInt, inputs(1).toInt)
          if (error != controllerComponent.Error.NoError) {
            errorMessage(error)
            processPlayerTurn()
          }
          else {
            if (controller.checkMill(inputs(1).toInt)) {
              processMill()
            }
            controller.endPlayersTurn()
          }
        }

        catch {
          case ioobe: IndexOutOfBoundsException => {
            LOG.error(errorMessage(InputError))
          }
          case nfe: NumberFormatException => LOG.error(errorMessage(InputError))
        }
    }
  }

  def processMill(): Unit = {
    println("Player " + controller.playerOnTurn + " got a Mill. Please select a man to remove")
    try {
      val input = readInt()
      val error = controller.caseOfMill(input)
      if (error == controllerComponent.Error.NoError) {
        errorMessage(error)
        processMill()
      }
    } catch {
      case nfe: NumberFormatException => errorMessage(InputError)
    }

  }

  reactions += {
    case _: FieldChanged =>
      println(controller.gameboardToString)
      processInputLine("")
    case _: PlayerPhaseChanged =>
      println("Player: " + controller.playerOnTurn.name + " ------ Gamephase: " + controller.playerOnTurn.phase + " Man")
    case _: GameOver =>
      if (controller.playerOnTurn.equals(controller.playerWhite)) println("Black won the game!")
      else if (controller.playerOnTurn.equals(controller.playerBlack)) println("White won the game!")
    case _: CaseOfMill =>
      println("Player " + controller.playerOnTurn + " got a mill. Please select a man to remove")
    case _: StartNewGame => {
      gamestarted = true
      processInputLine("")
    }
  }
}
