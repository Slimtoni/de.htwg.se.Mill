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
  val tui = Tui(controller)
  val gui = new SwingGui(controller)

  def main(args: Array[String]): Unit = {
      tui.processInputLine()
  }
}