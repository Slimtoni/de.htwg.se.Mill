package de.htwg.se.NineMensMorris.model.gameboardComponent

import de.htwg.se.NineMensMorris.model.GameboardSize.GameboardSize
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.{Edge, Field, Gameboard}
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize}

import scala.collection.mutable


class GameboardFactory() {


  def createGameboard(size: GameboardSize): GameboardInterface = {
    val field0 = Field(0, FieldStatus.Empty, mutable.MutableList.empty)
    val field1 = Field(1, FieldStatus.Empty, mutable.MutableList.empty)
    val field2 = Field(2, FieldStatus.Empty, mutable.MutableList.empty)
    val field3 = Field(3, FieldStatus.Empty, mutable.MutableList.empty)
    val field4 = Field(4, FieldStatus.Empty, mutable.MutableList.empty)
    val field5 = Field(5, FieldStatus.Empty, mutable.MutableList.empty)
    val field6 = Field(6, FieldStatus.Empty, mutable.MutableList.empty)
    val field7 = Field(7, FieldStatus.Empty, mutable.MutableList.empty)
    val field8 = Field(8, FieldStatus.Empty, mutable.MutableList.empty)
    val field9 = Field(9, FieldStatus.Empty, mutable.MutableList.empty)
    val field10 = Field(10, FieldStatus.Empty, mutable.MutableList.empty)
    val field11 = Field(11, FieldStatus.Empty, mutable.MutableList.empty)
    val field12 = Field(12, FieldStatus.Empty, mutable.MutableList.empty)
    val field13 = Field(13, FieldStatus.Empty, mutable.MutableList.empty)
    val field14 = Field(14, FieldStatus.Empty, mutable.MutableList.empty)
    val field15 = Field(15, FieldStatus.Empty, mutable.MutableList.empty)
    val field16 = Field(16, FieldStatus.Empty, mutable.MutableList.empty)
    val field17 = Field(17, FieldStatus.Empty, mutable.MutableList.empty)
    val field18 = Field(18, FieldStatus.Empty, mutable.MutableList.empty)
    val field19 = Field(19, FieldStatus.Empty, mutable.MutableList.empty)
    val field20 = Field(20, FieldStatus.Empty, mutable.MutableList.empty)
    val field21 = Field(21, FieldStatus.Empty, mutable.MutableList.empty)
    val field22 = Field(22, FieldStatus.Empty, mutable.MutableList.empty)
    val field23 = Field(23, FieldStatus.Empty, mutable.MutableList.empty)


    size match {
      case GameboardSize.Six => {
        //print("small")
        val gameboard = Gameboard(new mutable.MutableList[FieldInterface], new mutable.MutableList[EdgeInterface])
        gameboard
      };
      case GameboardSize.Nine => {
        //println("large")
        val gameboard = Gameboard(new mutable.MutableList[FieldInterface], new mutable.MutableList[EdgeInterface])
        gameboard.addEdge(field0, field1)
        gameboard.addEdge(field1, field2)
        gameboard.addEdge(field3, field4)
        gameboard.addEdge(field4, field5)
        gameboard.addEdge(field6, field7)
        gameboard.addEdge(field7, field8)
        gameboard.addEdge(field9, field10)
        gameboard.addEdge(field10, field11)
        gameboard.addEdge(field12, field13)
        gameboard.addEdge(field13, field14)
        gameboard.addEdge(field15, field16)
        gameboard.addEdge(field16, field17)
        gameboard.addEdge(field18, field19)
        gameboard.addEdge(field19, field20)
        gameboard.addEdge(field21, field22)
        gameboard.addEdge(field22, field23)
        gameboard.addEdge(field0, field9)
        gameboard.addEdge(field9, field21)
        gameboard.addEdge(field3, field10)
        gameboard.addEdge(field10, field18)
        gameboard.addEdge(field6, field11)
        gameboard.addEdge(field11, field15)
        gameboard.addEdge(field1, field4)
        gameboard.addEdge(field4, field7)
        gameboard.addEdge(field16, field19)
        gameboard.addEdge(field19, field22)
        gameboard.addEdge(field8, field12)
        gameboard.addEdge(field12, field17)
        gameboard.addEdge(field5, field13)
        gameboard.addEdge(field13, field20)
        gameboard.addEdge(field2, field14)
        gameboard.addEdge(field14, field23)


        /*
        val v = gameboard.vertexList
        field0.millneigh = List((v(1), v(2)), (v(9), v(21)))
        field1.millneigh = List((field0, field2), (field4, field7))
        field2.millneigh = List((field0, field1), (field14, field23))
        field3.millneigh = List((field4, field5), (field10, field18))
        field4.millneigh = List((field3, field5), (field1, field7))
        field5.millneigh = List((field3, field4), (field13, field20))
        field6.millneigh = List((field7, field8), (field11, field15))
        field7.millneigh = List((field6, field8), (field1, field4))
        field8.millneigh = List((field6, field7), (field12, field17))
        field9.millneigh = List((field10, field11), (field0, field21))
        field10.millneigh = List((field9, field11), (field3, field18))
        field11.millneigh = List((field9, field10), (field6, field15))
        field12.millneigh = List((field13, field14), (field8, field17))
        field13.millneigh = List((field12, field14), (field5, field20))
        field14.millneigh = List((field12, field13), (field2, field23))
        field15.millneigh = List((field16, field17), (field6, field11))
        field16.millneigh = List((field15, field17), (field19, field22))
        field17.millneigh = List((field15, field16), (field8, field12))
        field18.millneigh = List((field19, field20), (field3, field10))
        field19.millneigh = List((field18, field20), (field16, field22))
        field20.millneigh = List((field18, field19), (field5, field13))
        field21.millneigh = List((field0, field9), (field22, field23))
        field22.millneigh = List((field21, field23), (field16, field19))
        field23.millneigh = List((field21, field22), (field2, field14))

        */

        gameboard
      };
    }
  }
}
