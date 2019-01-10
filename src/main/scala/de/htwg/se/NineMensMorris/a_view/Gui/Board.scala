package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.geom.{Ellipse2D, Line2D}
import java.awt.image.BufferedImage
import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import javafx.scene.shape.Circle
import javax.imageio.ImageIO
import javax.swing.border.Border

import scala.collection.mutable
import scala.swing.Swing.LineBorder
import scala.swing.{BoxPanel, Component, Dimension, FlowPanel, Graphics2D, GridPanel, Orientation, Point, Rectangle}

class Board(controller: ControllerInterface) extends Component {

  val panelDimension = new Dimension(650,650)
  minimumSize = panelDimension
  preferredSize = panelDimension
  maximumSize = panelDimension
  val imageBlack: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  val imageWhite: BufferedImage = ImageIO.read(new File("res/Black_50.png"))
  var currentImage: BufferedImage = _
  border = LineBorder(java.awt.Color.BLUE, 2)



  def getBoardList(): mutable.MutableList[(FieldInterface, Point)] = {
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
      else if(row == 2 || row == 6) {
        pointCount match {
          case 1 => xValue = constX1 + (constX2-constX1) * 1/3
          case 2 => xValue = constX2
          case 3 => xValue = constX3 - (constX3-constX2) * 1/3
        }
      }
      else if (row == 3 || row == 5) {
        pointCount match {
          case 1 => xValue = constX2 - (constX2-constX1) * 1/3
          case 2 => xValue = constX2
          case 3 => xValue = constX2 + (constX3-constX2) * 1/3
        }
      }
      else if (row == 4) {
        pointCount match {
          case 1 => xValue = constX1
          case 2 => xValue = constX1 + (constX2-constX1) * 1/3
          case 3 => xValue = constX2 - (constX2-constX1) * 1/3
          case 4 => xValue = constX2 + (constX3-constX2) * 1/3
          case 5 => xValue = constX3 - (constX3-constX2) * 1/3
          case 6 => xValue = constX3
        }
      }
      yValue = row * constY1
      vertexCords.+=((field, new Point(xValue - 100, yValue - 100)))

      if(row != 4 && pointCount == 3) {
        row += 1
        pointCount = 1
      } else if(row == 4 && pointCount == 6){
        row += 1
        pointCount = 1
      } else {
        pointCount +=1
      }
    }
    vertexCords
  }



  override def paintComponent(g:Graphics2D): Unit =
  {
    listenTo()
    val image = ImageIO.read(new File("res/Board.png"))

    g.drawImage(image, 0, 0, null)

    var lastVertex: (FieldInterface, Point) = null
    val vertexCords = getBoardList()
    for (i <- vertexCords) {
      println(i._2)
      var counter = new Ellipse2D.Double(i._2.x,i._2.y, 50, 50)
      //counter
      //g.draw(new Ellipse2D.Double(i._2.x,i._2.y, 50, 50))
      //g.fillOval(i._2.x, i._2.y, 50 , 50)
      //if (lastVertex != null) g.draw(new Line2D.Double(i._2.x,i._2.y,lastVertex._2.x,lastVertex._2.y))
      lastVertex = i
    }
    //g.drawImage(ImageIO.read(new File("res/Board.png")), 0, 0, null)
    /*g.draw(new Line2D.Double(40,40,760, 40))
    g.draw(new Line2D.Double(40,760,760, 760))
    g.draw(new Line2D.Double(40,40,40, 760))
    g.draw(new Line2D.Double(40,40,760, 40))
    g.draw(new Line2D.Double(40,40,760, 40))*/
    //g.draw(new Line2D.Double(35,35,))

    //g.drawImage(imageBlack, 10, 10, null)

  }
}

class RowBoardPanel(rows: Int, cols: Int) extends GridPanel(rows, cols) {
  border = LineBorder(java.awt.Color.RED, 2)

  override def paintComponent(g:Graphics2D): Unit =
  {
  }
}