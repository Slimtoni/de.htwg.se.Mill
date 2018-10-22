package de.htwg.se.Mill

import de.htwg.se.Mill.model._

object Mill {
  def main(args: Array[String]): Unit = {
    val student = Player("Toni")
    println("Hello, " + student.name)

    val gameboard = new Gameboard[Field](4)
    val field1 = new Field(FieldStatus.Empty)
    val field2 = new Field(FieldStatus.Black)
    val field3 = new Field(FieldStatus.Empty)
    val field4 = new Field(FieldStatus.Empty)

    gameboard.addVertex(field1)
    gameboard.addVertex(field1)
    gameboard.addVertex(field2)

    gameboard.addEdge(field1, field2)
    gameboard.addEdge(field2, field3)
    gameboard.addEdge(field1, field2)
    val list = gameboard.vertList()
    list.foreach(println)
    val list2 = gameboard.nbourList()
    list2.foreach(println)
    print(list2.contains((field1, field2)))
  }
}
