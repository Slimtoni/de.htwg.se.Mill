package de.htwg.se.NineMensMorris.a_view.Gui

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent.{ControllerInterface, FieldChanged, GamePhaseChanged, PlayerPhaseChanged}
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface

import javax.imageio.ImageIO
import javax.swing._

import java.io.File

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
  val vertexList: mutable.MutableList[(FieldInterface, Point)] = board.getBoardList

  val chooseFileButton = new Button("Choose file")
  var fieldButs: mutable.MutableList[FieldButton] = mutable.MutableList.empty
  val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  for (i <- 0 to 23) {
    val fieldtmp = FieldButton(i)
    listenTo(fieldtmp)
    if (fieldtmp != null) fieldButs.+=(fieldtmp)
  }
  val dummyBut: FieldButton = new FieldButton(100) {
    visible = false
  }

  def mouseClick(xCor: Int, yCor: Int, boardDim: Dimension): Option[FieldInterface] = {
    for (i <- vertexList) {
      if ((xCor >= i._2.x && xCor <= i._2.x + 50) && (yCor >= i._2.y && yCor <= i._2.y + 50))
        return Some(i._1)
    }
    None
  }

  var firstClick = true
  var clickOne = 0

  def clickHandler(id: Int): Unit = {
    val dummyTargetId = 0
    controller.checkPlayer(controller.getPlayerOnTurn)
    if (controller.getPlayerOnTurnPhase == "Move" || controller.getPlayerOnTurnPhase == "Fly") {
      if (firstClick) {
        controller.getField(id) match {
          case Some(value) =>
            if (value.fieldStatus.toString == controller.getPlayerOnTurn) {
              clickOne = id
              firstClick = false
              statusPanel.setInfo("Field " + id +  " selected! Choose second Field to " +
                controller.getPlayerOnTurnPhase + "!")
            } else {
              statusPanel.setInfo("Please select one of your own mens to " + controller.getPlayerOnTurnPhase)
            }
        }

      } else {
        if (id == clickOne) {
          clickOne = 0
          firstClick = true
          statusPanel.setInfo("Field " + id +  " diselected! Choose a Man to " +
            controller.getPlayerOnTurnPhase + "!")
        } else {
          val error = controller.performTurn(clickOne, id)
          if (error == controllerComponent.Error.NoError) {
            firstClick = true
            statusPanel.setInfo("Succesfully moved Man from Field " + clickOne + " to Field " + id)
          } else {

          }
        }
      }
    } else {
      val error = controller.performTurn(id, dummyTargetId)
      if (error != controllerComponent.Error.NoError) {
        statusPanel.setInfo(error.toString)
      } else {
        statusPanel.setInfo("Succesfully placed Man on the Field " + id)
      }
    }
  }

  val statusPanel = new StatusPanel(controller)
  statusPanel.visible = false
  listenTo(statusPanel)
  listenTo(controller)

  val mainPanel: FlowPanel = new FlowPanel() {
    this.visible = false
    this.enabled = false
    contents += new BoxPanel(Orientation.Vertical) {
      listenTo(this.mouse.clicks)
      contents += board
      reactions += {
        case MousePressed(_, point, _, _, _) =>
          mouseClick(point.x, point.y, this.size) match {
            case Some(value) =>
              clickHandler(value.id)
            case None => println("No Button clicked") //TODO: insert log
          }
      }
    }
  }

  val startPanel: FlowPanel = new FlowPanel() {
    visible = true
    var startButton = new Button("Start Game")
    contents += startButton
    listenTo(startButton)
    reactions += {
      case ButtonClicked(_) =>
        //TODO: Methode für Spielstart
        this.visible = false
        mainPanel.visible = true
        mainPanel.enabled = true
        statusPanel.visible = true
        //board.repaint()
        statusPanel.refresh()
    }
  }

  def startGame(): Unit = {
    controller.changePlayerOnTurn()
  }

  contents = new BorderPanel {
    add(mainPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
    add(startPanel, BorderPanel.Position.North)
  }
  reactions += {
    case ButtonClicked(buttonPressed) => {
      for (i <- fieldButs)
        if (buttonPressed == i) println(i.id)
    }
    case x: FieldChanged => board.repaint()
      statusPanel.refresh()
    case _: PlayerPhaseChanged => statusPanel.refresh()
  }
  pack()
  centerOnScreen()
  open()
}
