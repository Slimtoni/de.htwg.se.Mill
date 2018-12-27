package de.htwg.se.NineMensMorris
import de.htwg.se.NineMensMorris.a_view.Tui
import de.htwg.se.NineMensMorris.controller.impl.DefaultGameController
import de.htwg.se.NineMensMorris.model._
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardFactory

import scala.io.StdIn.readLine

object NineMensMorris {
  var gameboardFactory = new GameboardFactory()
  val controller = new DefaultGameController(gameboardFactory.createGameboard(GameboardSize.Large))
  val tui = Tui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}