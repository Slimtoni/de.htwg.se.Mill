package de.htwg.se.NineMensMorris.a_view.Gui

import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import javax.imageio.ImageIO

import scala.swing.{Dimension, Graphics2D, GridPanel}

class BoardPanel(controller: ControllerInterface, rows: Int, cols: Int) extends GridPanel(rows, cols){

  minimumSize = new Dimension(700,728)
  override def paintComponent(g:Graphics2D): Unit =
  {
    g.drawImage(ImageIO.read(new File("res/Board.png")), 0, 0, null)
  }
}

class RowBoardPanel(rows: Int, cols: Int) extends GridPanel(rows, cols) {
  override def paintComponent(g:Graphics2D): Unit =
  {
  }
}