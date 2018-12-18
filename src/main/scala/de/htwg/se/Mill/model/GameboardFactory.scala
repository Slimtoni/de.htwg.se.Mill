package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.EdgeDirection.EdgeDirection
import de.htwg.se.Mill.model.GameboardSize.GameboardSize

import scala.collection.mutable

object GameboardSize extends Enumeration {
  type GameboardSize = Value
  val Small, Large = Value
}

class GameboardFactory() {


  def createGameboard(size: GameboardSize): Gameboard = {
    val field0 = Field(0, FieldStatus.Empty)
    val field1 = Field(1, FieldStatus.Empty)
    val field2 = Field(2, FieldStatus.Empty)
    val field3 = Field(3, FieldStatus.Empty)
    val field4 = Field(4, FieldStatus.Empty)
    val field5 = Field(5, FieldStatus.Empty)
    val field6 = Field(6, FieldStatus.Empty)
    val field7 = Field(7, FieldStatus.Empty)
    val field8 = Field(8, FieldStatus.Empty)
    val field9 = Field(9, FieldStatus.Empty)
    val field10 = Field(10, FieldStatus.Empty)
    val field11 = Field(11, FieldStatus.Empty)
    val field12 = Field(12, FieldStatus.Empty)
    val field13 = Field(13, FieldStatus.Empty)
    val field14 = Field(14, FieldStatus.Empty)
    val field15 = Field(15, FieldStatus.Empty)
    val field16 = Field(16, FieldStatus.Empty)
    val field17 = Field(17, FieldStatus.Empty)
    val field18 = Field(18, FieldStatus.Empty)
    val field19 = Field(19, FieldStatus.Empty)
    val field20 = Field(20, FieldStatus.Empty)
    val field21 = Field(21, FieldStatus.Empty)
    val field22 = Field(22, FieldStatus.Empty)
    val field23 = Field(23, FieldStatus.Empty)
    size match {
      case GameboardSize.Small => {
        //print("small")
        val gameboard = new Gameboard(new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
        gameboard.addEdge(field0, field1, EdgeDirection.Horizontal)
        gameboard.addEdge(field1, field2, EdgeDirection.Horizontal)
        gameboard.addEdge(field0, field3, EdgeDirection.Vertical)
        gameboard.addEdge(field2, field4, EdgeDirection.Vertical)
        gameboard.addEdge(field3, field5, EdgeDirection.Vertical)
        gameboard.addEdge(field5, field6, EdgeDirection.Horizontal)
        gameboard.addEdge(field4, field7, EdgeDirection.Vertical)
        gameboard.addEdge(field6, field7, EdgeDirection.Horizontal)
        gameboard
      };
      case GameboardSize.Large => {
        //println("large")
        val gameboard = new Gameboard(new mutable.MutableList[Field], new mutable.MutableList[Edge[Field]])
        gameboard.addEdge(field0, field1, EdgeDirection.Horizontal)
        gameboard.addEdge(field1, field2, EdgeDirection.Horizontal)
        gameboard.addEdge(field3, field4, EdgeDirection.Horizontal)
        gameboard.addEdge(field4, field5, EdgeDirection.Horizontal)
        gameboard.addEdge(field6, field7, EdgeDirection.Horizontal)
        gameboard.addEdge(field7, field8, EdgeDirection.Horizontal)
        gameboard.addEdge(field9, field10, EdgeDirection.Horizontal)
        gameboard.addEdge(field10, field11, EdgeDirection.Horizontal)
        gameboard.addEdge(field12, field13, EdgeDirection.Horizontal)
        gameboard.addEdge(field13, field14, EdgeDirection.Horizontal)
        gameboard.addEdge(field15, field16, EdgeDirection.Horizontal)
        gameboard.addEdge(field16, field17, EdgeDirection.Horizontal)
        gameboard.addEdge(field18, field19, EdgeDirection.Horizontal)
        gameboard.addEdge(field19, field20, EdgeDirection.Horizontal)
        gameboard.addEdge(field21, field22, EdgeDirection.Horizontal)
        gameboard.addEdge(field22, field23, EdgeDirection.Horizontal)
        gameboard.addEdge(field0, field9, EdgeDirection.Vertical)
        gameboard.addEdge(field9, field21, EdgeDirection.Vertical)
        gameboard.addEdge(field3, field10, EdgeDirection.Vertical)
        gameboard.addEdge(field10, field18, EdgeDirection.Vertical)
        gameboard.addEdge(field6, field11, EdgeDirection.Vertical)
        gameboard.addEdge(field11, field15, EdgeDirection.Vertical)
        gameboard.addEdge(field1, field4, EdgeDirection.Vertical)
        gameboard.addEdge(field4, field7, EdgeDirection.Vertical)
        gameboard.addEdge(field16, field19, EdgeDirection.Vertical)
        gameboard.addEdge(field19, field22, EdgeDirection.Vertical)
        gameboard.addEdge(field8, field12, EdgeDirection.Vertical)
        gameboard.addEdge(field12, field17, EdgeDirection.Vertical)
        gameboard.addEdge(field5, field13, EdgeDirection.Vertical)
        gameboard.addEdge(field13, field20, EdgeDirection.Vertical)
        gameboard.addEdge(field2, field14, EdgeDirection.Vertical)
        gameboard.addEdge(field14, field23, EdgeDirection.Vertical)
        gameboard
      };
    }
  }
}
