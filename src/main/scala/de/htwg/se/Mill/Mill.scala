package de.htwg.se.Mill

import de.htwg.se.Mill.model
import de.htwg.se.Mill.model.{Field, FieldStatus, Gameboard, Player}

import scala.collection.mutable

object Mill {
  def main(args: Array[String]): Unit = {
    val student = Player("Toni")
    println("Hello, " + student.name)

    val gameboard = new Gameboard[Field](new mutable.MutableList[Field], new mutable.MutableList[(Field, Field, Boolean)])
    gameboard.addEdge(new Field(0, FieldStatus.Empty), new Field(1, FieldStatus.Empty), true)
    gameboard.addEdge(new Field(1, FieldStatus.Empty), new Field(2, FieldStatus.Empty), false)
    gameboard.addEdge(new Field(2, FieldStatus.Empty), new Field(3, FieldStatus.Empty), true)
    gameboard.addEdge(new Field(3, FieldStatus.Empty), new Field(0, FieldStatus.Empty), false)



    var gameboardString = gameboard.toString()
  }
}
//"O__O"
//"|  |"
//"B__W"