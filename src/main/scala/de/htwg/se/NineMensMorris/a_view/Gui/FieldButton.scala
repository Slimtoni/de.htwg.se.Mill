package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.image.BufferedImage

import scala.swing.event.Event
import scala.swing.{Button, Dimension, Graphics2D, Insets}

case class FieldButton(id: Int) extends Button{
  val fieldDimension = new Dimension(59,59)
  var currentImage: BufferedImage = _
  margin = new Insets(5,20,5, 400)
  borderPainted = false
  minimumSize = fieldDimension
  preferredSize = fieldDimension
  maximumSize = fieldDimension

  override def publish(e: Event): Unit = super.publish(e)

  override def paintComponent(g: Graphics2D): Unit = {
  }
}
