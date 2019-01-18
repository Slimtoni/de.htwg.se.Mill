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
        gameboard
      };
    }
  }

  def setEdgeList(size: GameboardSize, gameboard: Gameboard): Gameboard = {
    size match {
      case GameboardSize.Six => {
        //print("small")
        //val gameboard = Gameboard(new mutable.MutableList[FieldInterface], new mutable.MutableList[EdgeInterface])
        gameboard
      };
      case GameboardSize.Nine => {
        println("large")
        //val gameboard = Gameboard(new mutable.MutableList[FieldInterface], new mutable.MutableList[EdgeInterface])
        gameboard.addEdge(gameboard.getField(0), gameboard.getField(1))
        gameboard.addEdge(gameboard.getField(1), gameboard.getField(2))
        gameboard.addEdge(gameboard.getField(3), gameboard.getField(4))
        gameboard.addEdge(gameboard.getField(4), gameboard.getField(5))
        gameboard.addEdge(gameboard.getField(6), gameboard.getField(7))
        gameboard.addEdge(gameboard.getField(7), gameboard.getField(8))
        gameboard.addEdge(gameboard.getField(9), gameboard.getField(10))
        gameboard.addEdge(gameboard.getField(10), gameboard.getField(11))
        gameboard.addEdge(gameboard.getField(12), gameboard.getField(13))
        gameboard.addEdge(gameboard.getField(13), gameboard.getField(14))
        gameboard.addEdge(gameboard.getField(15), gameboard.getField(16))
        gameboard.addEdge(gameboard.getField(16), gameboard.getField(17))
        gameboard.addEdge(gameboard.getField(18), gameboard.getField(19))
        gameboard.addEdge(gameboard.getField(19), gameboard.getField(20))
        gameboard.addEdge(gameboard.getField(21), gameboard.getField(22))
        gameboard.addEdge(gameboard.getField(22), gameboard.getField(23))
        gameboard.addEdge(gameboard.getField(0), gameboard.getField(9))
        gameboard.addEdge(gameboard.getField(9), gameboard.getField(21))
        gameboard.addEdge(gameboard.getField(3), gameboard.getField(10))
        gameboard.addEdge(gameboard.getField(10), gameboard.getField(18))
        gameboard.addEdge(gameboard.getField(6), gameboard.getField(11))
        gameboard.addEdge(gameboard.getField(0), gameboard.getField(15))
        gameboard.addEdge(gameboard.getField(1), gameboard.getField(4))
        gameboard.addEdge(gameboard.getField(4), gameboard.getField(7))
        gameboard.addEdge(gameboard.getField(16), gameboard.getField(19))
        gameboard.addEdge(gameboard.getField(19), gameboard.getField(22))
        gameboard.addEdge(gameboard.getField(8), gameboard.getField(12))
        gameboard.addEdge(gameboard.getField(12), gameboard.getField(17))
        gameboard.addEdge(gameboard.getField(5), gameboard.getField(13))
        gameboard.addEdge(gameboard.getField(13), gameboard.getField(20))
        gameboard.addEdge(gameboard.getField(2), gameboard.getField(14))
        gameboard.addEdge(gameboard.getField(14), gameboard.getField(23))
        gameboard
      }
    }
  }
}
