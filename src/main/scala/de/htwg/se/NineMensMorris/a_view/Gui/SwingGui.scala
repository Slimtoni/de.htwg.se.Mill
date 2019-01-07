package de.htwg.se.NineMensMorris.a_view.Gui

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface
import javax.swing._

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{ButtonClicked}


class SwingGui(controller: ControllerInterface) extends Frame {
  title = "NineMensMorris"
  visible = true
  minimumSize = new Dimension(700, 728)
  val chooseFileButton = new Button("Choose file")
  var fieldButs: mutable.MutableList[FieldButton] = _
  val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  for (_ <- 0 to 23) {
    fieldButs.+=(new FieldButton)
  }
  val dummyBut: Button = new Button {
    visible = false
  }

  listenTo(fieldButs(0))
  listenTo(fieldButs(1))
  //val fields =

  contents = new BoardPanel(controller, 7, 7) {
    //contents.update(0,f0But)
    //contents.update(1,f1But)
    contents += fieldButs(0)
    contents += dummyBut
    contents += dummyBut
    contents += fieldButs(1)
    contents += dummyBut
    contents += dummyBut
    contents += fieldButs(2)
    contents += dummyBut

  }

  reactions += {
    case ButtonClicked(_) => {
      Console.println("zegfzewgzi")
    }
  }

  pack()
  centerOnScreen()
  open()
}
