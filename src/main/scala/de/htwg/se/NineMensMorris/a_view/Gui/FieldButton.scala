package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

import scala.swing.{Button, Graphics2D}

class FieldButton extends Button{
  val imageBlack: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  val imageWhite: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  var currentImage: BufferedImage = _
  borderPainted = false

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(imageBlack, 0, 0, null)
  }
}
