package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import javax.imageio.ImageIO

import scala.collection.mutable
import scala.swing.{Component, Dimension, Graphics2D, Point}

class Board(controller: ControllerInterface, var overlayOn: Boolean = false) extends Component {

  val panelDimension = new Dimension(650, 650)
  minimumSize = panelDimension
  preferredSize = panelDimension
  maximumSize = panelDimension
  var currentImage: BufferedImage = _
  var backgroundImage: BufferedImage = ImageIO.read(new File("res/Board.png"))
  listenTo(controller)
  controller.createGameboard()

  def setOverlay(): Unit = {
    backgroundImage = ImageIO.read(new File("res/BoardSkin.jpg"))
    overlayOn = true
    repaint()
  }
  def unsetOverlay(): Unit = {
    backgroundImage = ImageIO.read(new File("res/Board.png"))
    overlayOn = false
    repaint()
  }

  def getBoardList: mutable.MutableList[(FieldInterface, Point)] = {
    val vertexCords: mutable.MutableList[(FieldInterface, Point)] = new mutable.MutableList[(FieldInterface, Point)]
    val vertexListTMP: mutable.MutableList[FieldInterface] = controller.getVertexList
    val constX1 = 100
    val constX2 = 400
    val constX3 = 700
    val constY1 = 100
    var row = 1
    var pointCount = 1
    var xValue: Int = constX1
    var yValue: Int = constY1
    for (field <- vertexListTMP) {
      if (row == 1 || row == 7) {
        pointCount match {
          case 1 => xValue = constX1
          case 2 => xValue = constX2
          case 3 => xValue = constX3
        }
      }
      else if (row == 2 || row == 6) {
        pointCount match {
          case 1 => xValue = constX1 + (constX2 - constX1) * 1 / 3
          case 2 => xValue = constX2
          case 3 => xValue = constX3 - (constX3 - constX2) * 1 / 3
        }
      }
      else if (row == 3 || row == 5) {
        pointCount match {
          case 1 => xValue = constX2 - (constX2 - constX1) * 1 / 3
          case 2 => xValue = constX2
          case 3 => xValue = constX2 + (constX3 - constX2) * 1 / 3
        }
      }
      else if (row == 4) {
        pointCount match {
          case 1 => xValue = constX1
          case 2 => xValue = constX1 + (constX2 - constX1) * 1 / 3
          case 3 => xValue = constX2 - (constX2 - constX1) * 1 / 3
          case 4 => xValue = constX2 + (constX3 - constX2) * 1 / 3
          case 5 => xValue = constX3 - (constX3 - constX2) * 1 / 3
          case 6 => xValue = constX3
        }
      }
      yValue = row * constY1
      vertexCords.+=((field, new Point(xValue - 100, yValue - 100)))

      if (row != 4 && pointCount == 3) {
        row += 1
        pointCount = 1
      } else if (row == 4 && pointCount == 6) {
        row += 1
        pointCount = 1
      } else {
        pointCount += 1
      }
    }
    vertexCords
  }

  override def paintComponent(g: Graphics2D): Unit = {
    g.drawImage(backgroundImage, 0, 0, null)

    val vertexCords = getBoardList
    for (i <- vertexCords) {
      i._1.fieldStatus match {
        case FieldStatus.Black =>
          if (overlayOn) {
            g.setColor(new Color(41, 163, 163))
            g.fillRect(i._2.x, i._2.y, 55, 55)
            g.setColor(Color.BLACK)
            g.drawRect(i._2.x,i._2.y, 55, 55)
            g.drawLine(i._2.x,i._2.y,i._2.x + 55,i._2.y + 55)
            g.drawLine(i._2.x + 55, i._2.y ,i._2.x ,i._2.y + 55)
          }
          else {
            g.setColor(Color.BLACK)
            g.fillOval(i._2.x, i._2.y, 55, 55)
            g.setColor(Color.GRAY)
            g.drawOval(i._2.x, i._2.y, 55, 55)
            g.drawOval(i._2.x + 15, i._2.y + 15, 25, 25)
          }
        case FieldStatus.White =>
          if (overlayOn) {
            g.setColor(new Color(255, 219, 56))
            g.fillRect(i._2.x, i._2.y, 55, 55)
            g.setColor(Color.BLACK)
            g.drawRect(i._2.x,i._2.y, 55, 55)
            g.drawLine(i._2.x,i._2.y,i._2.x + 55,i._2.y + 55)
            g.drawLine(i._2.x + 55, i._2.y ,i._2.x ,i._2.y + 55)
          }
          else {
            g.setColor(Color.WHITE)
            g.fillOval(i._2.x, i._2.y, 55, 55)
            g.setColor(Color.GRAY)
            g.drawOval(i._2.x, i._2.y, 55, 55)
            g.drawOval(i._2.x + 15, i._2.y + 15, 25, 25)
          }
        case FieldStatus.Empty =>
      }
    }
  }
}