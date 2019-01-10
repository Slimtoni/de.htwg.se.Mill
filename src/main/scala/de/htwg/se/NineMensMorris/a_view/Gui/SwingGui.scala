package de.htwg.se.NineMensMorris.a_view.Gui

import de.htwg.se.NineMensMorris.controller.controllerComponent.{ControllerInterface, PlayerPhaseChanged}
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.Field
import javax.swing._

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{ButtonClicked, MousePressed}


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  resizable = false
  val framesize = new Dimension(650, 700)
  minimumSize = framesize
  preferredSize = framesize
  maximumSize = framesize

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

  //listenTo(fieldButs(1))
  //val fields =

  /*contents = new Board(controller, 7, 7) {

    /*contents += new RowBoardPanel(1,3) {
      contents += fieldButs.head
      //contents += dummyBut
      //contents += dummyBut
      contents += fieldButs(1)
      //contents += dummyBut
      //contents += dummyBut
      contents += fieldButs(2)
    }
    contents += new RowBoardPanel(1,3) {
      contents += fieldButs(3)
      contents += fieldButs(4)
      contents += fieldButs(5)
    }
    contents += new RowBoardPanel(1,3) {
      contents += fieldButs(6)
      contents += fieldButs(7)
      contents += fieldButs(8)
    }
    contents += new RowBoardPanel(1,6) {
      contents += fieldButs(12)
      contents += fieldButs(13)
      contents += fieldButs(14)
      contents += fieldButs(15)
      contents += fieldButs(16)
      contents += fieldButs(17)
    }
    contents += new RowBoardPanel(1,3) {
      contents += fieldButs(9)
      contents += fieldButs(10)
      contents += fieldButs(11)
    }
    contents += new RowBoardPanel(1,3) {
      contents += fieldButs(18)
      contents += fieldButs(19)
      contents += fieldButs(20)
    }
    contents += new RowBoardPanel(1,3) {
      contents += fieldButs(21)
      contents += fieldButs(22)
      contents += fieldButs(23)
    }
    //for (fieldB <- fieldButs)
     // contents += fieldB
    //contents.update(0,f0But)
    //contents.update(1,f1But)
    //contents += field
    /*contents += fieldButs(0)
    contents += dummyBut
    contents += dummyBut
    contents += fieldButs(1)
    contents += dummyBut
    contents += dummyBut
    contents += fieldButs(2)
    contents += dummyBut*/
    */

  }*/
  val mainPanel: FlowPanel = new FlowPanel() {
    contents += new BoxPanel(Orientation.Vertical) {
      listenTo(this.mouse.clicks)
      contents += board
      reactions += {
        case MousePressed(com, point, _, _,_) =>
          mouseClick(point.x,point.y, this.size) match {
            case Some(value) => println(value.id + " clicked!")
            case None => println("No Button clicked")
          }
      }
    }
  }

  val statusPanel: GridPanel = new GridPanel(1,2) {
    val currentPlayerLabel = new Label("Current Player: " + controller.getPlayerOnTurn)
    currentPlayerLabel.horizontalAlignment = Alignment.Left
    val playerPhaseLabel = new Label("Current Players Gamephase: " + controller.getPlayerOnTurnPhase)
    playerPhaseLabel.horizontalAlignment = Alignment.Right
    contents += currentPlayerLabel
    contents += playerPhaseLabel
  }


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
    case _: PlayerPhaseChanged =>
  }

  pack()
  centerOnScreen()
  open()
}
