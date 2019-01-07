package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.{BasicStroke, Color, FlowLayout, Graphics}
import java.awt.geom.Line2D
import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import javafx.scene.shape.Circle
import javax.imageio.ImageIO
import javax.swing.{Icon, ImageIcon, JLabel}

import scala.swing.{Component, Dimension, Graphics2D, GridPanel, Image, Label}

class BoardPanel(controller: ControllerInterface, rows: Int, cols: Int) extends GridPanel(rows, cols){
  val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  opaque = false
  background = new Color(0,0,0,0)
  var field0 = new Label {
    icon = blackIcon
    opaque = false
  }

  contents += field0
/*
  override def paint(g: Graphics2D): Unit = {
    listenTo(controller)
    val background : Image = ImageIO.read(new File("res/Board.png"))
    val blackIcon: Icon = new ImageIcon("res/Black_50.png")
    var field0 = new Label {
      icon = blackIcon
    }

    field0.maximumSize = new Dimension(50,50)
    val flowLayout = new FlowLayout
    flowLayout.setAlignment(FlowLayout.CENTER)

    //g.drawImage(background, 0,0,null)
    //g.draw

    /*g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON)
    g.setColor(new Color(220, 179, 92))
    g.fillRect(0, 0, preferredSize.width ,preferredSize.height)
    val edgeAssumption = 50
    val squareSideAssumption = preferredSize.height - edgeAssumption min preferredSize.width - edgeAssumption
    val wid = squareSideAssumption / 23
    val squareSide = wid * 23
    val delta = (squareSideAssumption - squareSide) / 2
    val circleSize = (wid * 0.9).toInt
    val deltaEdge = (edgeAssumption/2) - circleSize / 2 + delta
    val x0 = (preferredSize.width - squareSide)/2
    val y0 = (preferredSize.height - squareSide)/2
    for (x <- 0 to 23)  g.draw(new Line2D.Double(x0 + x * wid, y0, x0 + x * wid, y0 + squareSide))
    for (y <- 0 to 23)   g.draw(new Line2D.Double(x0, y0 + y * wid, x0 + squareSide, y0 + y * wid))

    g.setStroke(new BasicStroke(3f))*/
  }*/
}
