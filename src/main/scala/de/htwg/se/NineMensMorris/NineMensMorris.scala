package de.htwg.se.NineMensMorris
import de.htwg.se.NineMensMorris.a_view.Gui.SwingGui
import de.htwg.se.NineMensMorris.a_view.Tui
import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.ControllerMill
import de.htwg.se.NineMensMorris.model._
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardFactory

import scala.io.StdIn.readLine

object NineMensMorris {
  var gameboardFactory = new GameboardFactory()
  val controller = new ControllerMill(gameboardFactory.createGameboard(GameboardSize.Nine))


  def main(args: Array[String]): Unit = {
    val _ = new SwingGui(controller)
    val tui = new Tui(controller)
    println("Nine mens morris by Toni & Matze\n" +
      "--------------------------------\n" +
      "n - start new game\n" +
      "q - quit\n" +
      "s - save the game\n" +
      "l - load the game\n" +
      "g - go on to next move\n" +
      "--------------------------------")
    var input = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}