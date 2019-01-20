package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO

import scala.swing.event.Event
import scala.swing.{Button, Dimension, Graphics2D, Insets, Point}

case class FieldButton(id: Int) extends Button{
  val fieldDimension = new Dimension(59,59)
  var currentImage: BufferedImage = _
  margin = new Insets(5,20,5, 400)
  borderPainted = false
  minimumSize = fieldDimension
  preferredSize = fieldDimension
  maximumSize = fieldDimension
  //locationOnScreen = new Point(0,0)

  override def publish(e: Event): Unit = super.publish(e)

  override def paintComponent(g: Graphics2D): Unit = {
    //g.drawImage(imageBlack, 10, 10, null)
  }
}
