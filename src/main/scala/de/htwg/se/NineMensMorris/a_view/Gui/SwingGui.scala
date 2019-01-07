package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.FlowLayout
import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import javax.imageio.ImageIO
import javax.swing._

import scala.swing._


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  minimumSize = new Dimension(700, 728)

  val boardPanel = new BoardPanel(controller, 7,7)
  //val setupPanel = new SetupPanel(controller)


  boardPanel.visible = true

  //val backgroundPanel = new JPanel()
  //val boardBackground : Image = ImageIO.read(new File("res/Board.png"))

  /*val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  var field0 = new JLabel(blackIcon)
  board.setLayout(new FlowLayout)
  board.add(field0)*/
  val backgroundImage: ImagePanel = new ImagePanel {imagePath = "res/Board.png"}
  val board: Label =  new Label()

  contents = new BorderPanel {
    //add(boardPanel, BorderPanel.Position.South)
    //add(backgroundImage, BorderPanel.Position.Center)
    add(board, BorderPanel.Position.Center)
  }
  //contents += boardPanel

  pack()
  centerOnScreen()
  open()
}
