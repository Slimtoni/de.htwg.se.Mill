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
  var fieldButs: mutable.MutableList[FieldButton] = mutable.MutableList.empty
  val blackIcon: Icon = new ImageIcon("res/Black_50.png")
  for (i <- 0 to 23) {
    val fieldtmp = FieldButton(i)
    println(fieldtmp)
    listenTo(fieldtmp)
    if (fieldtmp != null) fieldButs.+=(fieldtmp)
  }
  //val field: FieldButton = new FieldButton
  val dummyBut: FieldButton = new FieldButton(100) {
    visible = false
  }

  //listenTo(fieldButs(1))
  //val fields =

  contents = new BoardPanel(controller, 7, 7) {

    contents += new RowBoardPanel(1,7) {
      contents += fieldButs.head
      contents += dummyBut
      contents += dummyBut
      contents += fieldButs(1)
      contents += dummyBut
      contents += dummyBut
      contents += fieldButs(2)
    }
    contents += new RowBoardPanel(1,7) {
      contents += dummyBut
      contents += fieldButs(3)
      contents += dummyBut
      contents += fieldButs(4)
      contents += dummyBut
      contents += fieldButs(5)
      contents += dummyBut
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

  }

  reactions += {
    case ButtonClicked(buttonPressed) => {
      for (i <- fieldButs)
        if(buttonPressed == i) println(i.id)
      //Console.println("zegfzewgzi")
    }
  }

  pack()
  centerOnScreen()
  open()
}
