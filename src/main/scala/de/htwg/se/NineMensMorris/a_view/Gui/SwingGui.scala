package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.Color

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import javax.imageio.ImageIO
import javax.swing.{Icon, ImageIcon}
import java.io.{File, PrintWriter}

import de.htwg.se.NineMensMorris.controller.controllerComponent.Error.errorMessage

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{ButtonClicked, Key, MousePressed}


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  resizable = false
  val startFramesize = new Dimension(300, 100)
  val playFramesize = new Dimension(650, 750)
  minimumSize = startFramesize
  preferredSize = startFramesize
  maximumSize = playFramesize
  val icon: Image = ImageIO.read(new File("res/GameIcon.png"))
  iconImage = icon
  val board = new Board(controller)
  val vertexList: mutable.MutableList[(FieldInterface, Point)] = board.getBoardList

  val chooseFileButton = new Button("Choose file")
  var fieldButs: mutable.MutableList[FieldButton] = mutable.MutableList.empty
  var overlay = false
  var foundMill = false
  val startButton = new Button("Start Game")
  val loadButton = new Button("Load Game")

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
    if (!foundMill) {
      if (controller.getPlayerOnTurnPhase == "Move" || controller.getPlayerOnTurnPhase == "Fly") {
        if (firstClick) {
          controller.getField(id) match {
            case Some(value) =>
              if (value.fieldStatus.toString == controller.getPlayerOnTurn) {
                clickOne = id
                firstClick = false
                statusPanel.setInfo("Field " + id + " selected! Choose second Field to " +
                  controller.getPlayerOnTurnPhase + "!")
              } else {
                statusPanel.setInfo("Please select one of your own mens to " + controller.getPlayerOnTurnPhase)
              }
            case None => statusPanel.setInfo("Field doesnt exist!")
          }

        } else {
          if (id == clickOne) {
            clickOne = 0
            firstClick = true
            statusPanel.setInfo("Field " + id + " diselected! Choose a Man to " +
              controller.getPlayerOnTurnPhase + "!")
          } else {
            val error = controller.performTurn(clickOne, id)
            if (error != controllerComponent.Error.NoError) {
              statusPanel.setInfo(error.toString)
            } else {
              if (controller.checkMill(id)) {
                statusPanel.setMessage("Player " + controller.getPlayerOnTurn + " got a Mill. Please select a man to remove")
                foundMill = true
                board.repaint()
              } else controller.endPlayersTurn()
              firstClick = true
            }
          }
        }
      } else {
        val error = controller.performTurn(id, dummyTargetId)
        if (error != controllerComponent.Error.NoError) {
          statusPanel.setInfo(error.toString)
        } else {
          statusPanel.setInfo("Succesfully placed Man on the Field " + id)
          if (controller.checkMill(id)) {
            statusPanel.setMessage("Player " + controller.getPlayerOnTurn + " got a Mill. Please select a man to remove")
            foundMill = true
            board.repaint()
          } else controller.endPlayersTurn()
        }
      }
    } else {
      val error: Error.Value = controller.caseOfMill(id)
      if (error == controllerComponent.Error.NoError) {
        foundMill = false
        statusPanel.setInfo("Succesfully removed a Man on the Field " + id)
        controller.endPlayersTurn()
      } else if (error == controllerComponent.Error.KillManError) {
        foundMill = false
        statusPanel.setInfo(errorMessage(error))
        controller.endPlayersTurn()
      } else {
        statusPanel.setInfo(errorMessage(error))
      }
    }
  }

  menuBar = new MenuBar {
    visible = false
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") {
        controller.startNewGame()
      })
      contents += new MenuItem(Action("Save") {
        var saveString = ""
        val err: Error.Value = controller.save()
         err match {
          case Error.NoError => saveString = "Successfully saved the game!"
          case Error.LoadError => saveString = errorMessage(err)
        }
        Dialog.showMessage(mainPanel.contents.head, saveString, title = "Save Game")
      })
      contents += new MenuItem(Action("Load") { loadGame() })
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Options") {
      val checkbox = new CheckMenuItem("Overlay")
      mnemonic = Key.O

      contents += checkbox
      listenTo(checkbox)
      reactions += {
        case ButtonClicked(_) =>
          if (checkbox.selected) {
            board.setOverlay()
            setOverlay(new Color(255, 222, 99))
          }
          else {
            board.unsetOverlay()
            setOverlay(Color.WHITE)
          }
      }
    }
  }

  def setOverlay(color: Color): Unit = {
    menuBar.background = color
    this.background = color
    statusPanel.setBackgroundColor(color)
  }

  def loadGame(): Unit = {
    var loadString = ""
    val err: Error.Value = controller.load()
    err match {
      case Error.NoError => loadString = "Successfully loaded the game!"
      case Error.LoadError => loadString = errorMessage(err)
    }
    Dialog.showMessage(mainPanel.contents.head, loadString, "Load Game")
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
              if (!controller.gameOver) clickHandler(value.id)
            case None => println("No Button clicked") //TODO: insert log
          }
      }
    }
  }

  val startPanel: FlowPanel = new FlowPanel() {
    visible = true
    contents += startButton
    contents += loadButton
    listenTo(startButton, loadButton)
    reactions += {
      case ButtonClicked(`startButton`) => controller.startNewGame()
      case ButtonClicked(`loadButton`) => loadGame()
    }
  }

  def refreshAll(): Unit = {
    statusPanel.refresh()
    board.repaint()
    this.repaint()
    startPanel.repaint()
  }

  def startGame(): Unit = {
    minimumSize = playFramesize
    preferredSize = playFramesize
    menuBar.visible = true
    foundMill = false
    refreshAll()
    mainPanel.visible = true
    statusPanel.visible = true
    startPanel.visible = false
  }
  contents = new BorderPanel {
    add(mainPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
    add(startPanel, BorderPanel.Position.North)
  }

  reactions += {
    case _: FieldChanged => refreshAll()
    case _: PlayerPhaseChanged => refreshAll()
    case _: GameOver =>
      statusPanel.setMessage("")
      var winString = ""
      if (controller.playerOnTurn.equals(controller.playerWhite)) winString = "Black won the game!"
      else if (controller.playerOnTurn.equals(controller.playerBlack)) winString = "White won the game!"
      statusPanel.setInfo(winString)
      Dialog.showMessage(contents.head, winString, title = "Lost")
    case _: StartNewGame => startGame()
  }
  pack()
  centerOnScreen()
  open()
}
