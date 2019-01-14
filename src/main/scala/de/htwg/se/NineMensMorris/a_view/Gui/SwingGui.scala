package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.Color

import de.htwg.se.NineMensMorris.controller.controllerComponent
import de.htwg.se.NineMensMorris.controller.controllerComponent._
import de.htwg.se.NineMensMorris.model.gameboardComponent.FieldInterface
import javax.imageio.ImageIO
import javax.swing.{Icon, ImageIcon}
import java.io.File

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{ButtonClicked, Key, MousePressed}


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  resizable = false
  val framesize = new Dimension(650, 739)
  var foundMill = false
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
    if (!foundMill) {
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
            if (error != controllerComponent.Error.NoError) {
              statusPanel.setInfo(error.toString)
            } else {
              if (controller.checkMill(id)) {
                statusPanel.setMessage("Player " + controller.getPlayerOnTurn + " got a Mill. Please select a man to remove")
                foundMill = true
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
          } else controller.endPlayersTurn()
        }
      }
    } else {
      val error = controller.caseOfMill(id)
      if (error != controllerComponent.Error.NoError) {
        statusPanel.setInfo(error.toString)
      } else {
        foundMill = false
        statusPanel.setInfo("Succesfully removed a Man on the Field " + id)
        controller.endPlayersTurn()
      }
    }

  }


  menuBar = new MenuBar {
    background =  new Color(255, 222, 99)

    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.startNewGame() })
      contents += new MenuItem(Action("Random") {  })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {  })
      contents += new MenuItem(Action("Redo") {  })
    }
    contents += new Menu("Solve") {
      mnemonic = Key.S
      contents += new MenuItem(Action("Solve") {  })
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Show all candidates") { })
      contents += new MenuItem(Action("Size 1*1") {  })
      contents += new MenuItem(Action("Size 4*4") {  })
      contents += new MenuItem(Action("Size 9*9") {  })

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
        //Console.println("Start Game clicked")
        controller.startNewGame()
    }
  }

  def refreshAll(): Unit = {
    statusPanel.refresh()
    board.repaint()
    this.repaint()
    startPanel.repaint()
  }

  def startGame(): Unit = {
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
    case _: FieldChanged =>
      refreshAll()
    case _: PlayerPhaseChanged => refreshAll()
    case _: GamePhaseChanged =>
      println("GamePhaseChanged!!!")
      mainPanel.visible = false
      mainPanel.enabled = false
      statusPanel.visible = false
      startPanel.visible = true
    case _: StartNewGame => startGame()
  }
  pack()
  centerOnScreen()
  open()
}
