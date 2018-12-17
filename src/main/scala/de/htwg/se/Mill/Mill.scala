package de.htwg.se.Mill
import de.htwg.se.Mill.a_view.Tui
import de.htwg.se.Mill.controller.Controller
import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection
import de.htwg.se.Mill.model._
import scala.io.StdIn.readLine

object Mill {
  var gameboardFactory = new GameboardFactory()
  val controller = new Controller(gameboardFactory.createGameboard(GameboardSize.Large))
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (input != "q")
    /*var field0 = Field(0, FieldStatus.Empty)
    var field1 = Field(1, FieldStatus.Empty)
    var field2 = Field(2, FieldStatus.Empty)
    var field3 = Field(3, FieldStatus.Empty)
    var field4 = Field(4, FieldStatus.Empty)

    val gameboard = new Gameboard[Field](new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
    gameboard.addEdge(field0, field1, EdgeDirection.Horizontal)
    gameboard.addEdge(field1, field2, EdgeDirection.Vertical)
    gameboard.addEdge(field2, field3, EdgeDirection.Horizontal)
    gameboard.addEdge(field3, field0, EdgeDirection.Vertical)*/

    //field0.changeFieldStatus(FieldStatus.Black)

    //val gameboardFactory = new GameboardFactory()
    //val gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    println()
    //var gameboardString = gameboard.toString()
    //print(gameboardString)
  }
}
//"O__O"
//"|  |"
//"B__W"