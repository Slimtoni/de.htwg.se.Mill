package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.image.BufferedImage
import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.{ControllerInterface, FieldChanged, GamePhaseChanged, PlayerPhaseChanged}
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import javax.imageio.ImageIO
import javax.swing._

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{ButtonClicked, MousePressed}


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  resizable = false
  val framesize = new Dimension(650, 730)
  minimumSize = framesize
  preferredSize = framesize
  maximumSize = framesize
  val icon: Image = ImageIO.read(new File("res/GameIcon.png"))
  iconImage = icon



  val board = new Board(controller)
  val vertexList: mutable.MutableList[(FieldInterface, Point)] = board.getBoardList()

  val chooseFileButton = new Button("Choose file")
  var fieldButs: mutable.MutableList[FieldButton] = mutable.MutableList.empty
  val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  for (i <- 0 to 23) {
    val fieldtmp = FieldButton(i)
    listenTo(fieldtmp)
    if (fieldtmp != null) fieldButs.+=(fieldtmp)
  }
  //val field: FieldButton =new FieldButton
  val dummyBut: FieldButton = new FieldButton(100) {
    visible = false
  }

  def mouseClick(xCor: Int, yCor: Int, boardDim: Dimension): Option[FieldInterface] = {
    val x0 = 25
    val y0 = 25
    for (i <- vertexList) {
      if ((xCor >= i._2.x && xCor <= i._2.x + 50) && (yCor >= i._2.y && yCor <= i._2.y + 50))
        return Some(i._1)
    }
    None
  }

  val mainPanel: FlowPanel = new FlowPanel() {
    contents += new BoxPanel(Orientation.Vertical) {
      listenTo(this.mouse.clicks)
      contents += board
      reactions += {
        case MousePressed(com, point, _, _,_) =>
          mouseClick(point.x,point.y, this.size) match {
            case Some(value) => {

              println(value.id + " clicked!")
            }
            case None => println("No Button clicked")
          }
      }
    }
  }
  val statusPanel = new StatusPanel(controller)
  listenTo(statusPanel)
  listenTo(controller)




  contents = new BorderPanel {
    add(mainPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
  }

  reactions += {
    case ButtonClicked(buttonPressed) => {
      for (i <- fieldButs)
        if(buttonPressed == i) println(i.id)
      //Console.println("zegfzewgzi")
    }
    case x: FieldChanged => board.repaint()
    case _: PlayerPhaseChanged => statusPanel.refresh()
  }

  pack()
  centerOnScreen()
  open()
}
