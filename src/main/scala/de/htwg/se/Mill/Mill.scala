package de.htwg.se.Mill
import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection
import de.htwg.se.Mill.model._

import scala.collection.mutable

object Mill {
  def main(args: Array[String]): Unit = {
    val student = Player("Toni")
    println("Hello, " + student.name)
    var field0 = new Field(0, FieldStatus.Empty)
    var field1 = new Field(1, FieldStatus.Empty)
    var field2 = new Field(2, FieldStatus.Empty)
    var field3 = new Field(3, FieldStatus.Empty)
    var field4 = new Field(4, FieldStatus.Empty)

    val gameboard = new Gameboard[Field](new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
    gameboard.addEdge(field0, field1, EdgeDirection.Horizontal)
    gameboard.addEdge(field1, field2, EdgeDirection.Vertical)
    gameboard.addEdge(field2, field3, EdgeDirection.Horizontal)
    gameboard.addEdge(field3, field0, EdgeDirection.Vertical)

    field0.changeFieldStatus(FieldStatus.Black)

    print(gameboard)
    field0.changeFieldStatus(FieldStatus.Black)
    print(gameboard)
  }
}

