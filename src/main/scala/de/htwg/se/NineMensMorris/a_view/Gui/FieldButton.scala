package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

import scala.swing.event.Event
import scala.swing.{Button, Dimension, Graphics2D, Point}

case class FieldButton(id: Int) extends Button{
  val imageBlack: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  val imageWhite: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  var currentImage: BufferedImage = _
  borderPainted = false
  minimumSize =new Dimension(50,50)
  preferredSize = new Dimension(50,50)
  maximumSize = new Dimension(50,50)

  override def publish(e: Event): Unit = super.publish(e)

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(imageBlack, 10, 10, null)
  }
}
