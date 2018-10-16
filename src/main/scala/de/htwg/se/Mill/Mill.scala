package de.htwg.se.Mill

import de.htwg.se.Mill.model.Player
import de.htwg.se.Mill.model.Edge
import de.htwg.se.Mill.model.Graph
import de.htwg.se.Mill.model.Field
import de.htwg.se.Mill.model.FieldStatus

object Mill {
  def main(args: Array[String]): Unit = {
    val student = Player("Toni")
    println("Hello, " + student.name)

    val edge = new Edge[Int](1, 2)
    println(edge)
    println(edge.src())
    println("-------------------------------")

    val blue = FieldStatus.Black

  }
}
