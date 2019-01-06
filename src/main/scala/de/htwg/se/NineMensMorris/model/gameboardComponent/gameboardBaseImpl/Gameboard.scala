package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.Status.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl._
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardInterface}

import scala.collection.mutable

case class Gameboard(vertexList: mutable.MutableList[FieldInterface], neigh: mutable.MutableList[EdgeInterface]) extends GameboardInterface {

  def this() = this(mutable.MutableList[FieldInterface](), mutable.MutableList[EdgeInterface]())

  def getField(id: Int): FieldInterface = {
    for (i <- vertexList.iterator) if (i.id == id) return i
    Field(99,FieldStatus.Empty, List()) // error case with dummy Field
  }

  def addVertex(v: FieldInterface): Gameboard = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  def addEdge(v: FieldInterface, w: FieldInterface): Gameboard = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (!containsEdge(v,w) || !containsEdge(w,v)) {
      val edge = Edge(v,w)
      neigh.+=(edge)
    }
    copy(vertexList, neigh)
  }

  def containsVertex(v: FieldInterface): Boolean = {
    for (i<-this.vertexList) {
      if (v.checkID(i)) return true
    }
    false
  }

  def containsEdge(v: FieldInterface, w: FieldInterface): Boolean = {
    if (containsVertex(v) && containsVertex(w)) {
      val edge = Edge(v,w)
      for (i<-this.neigh) {
        //print(i)
        if (edge.equals(i)) return true
      }
    }
    false
  }

  def set(field: Int, fieldStatus: String): Option[Gameboard] = {
    val fieldtoChange: Option[FieldInterface] = vertexList.get(field)
    fieldtoChange match {
      case Some(f) => {
          fieldStatus match {
            case "Black" => vertexList(field) = f.changeFieldStatus(FieldStatus.Black)
            case "White" => vertexList(field) = f.changeFieldStatus(FieldStatus.White)
            case "Empty" => vertexList(field) = f.changeFieldStatus(FieldStatus.Empty)
            case _ =>       return None
          }
      }
      case None => return None
    }
    Option(copy(vertexList, neigh))
  }

  override def toString: String = {
    var gameboardString: String = ""
    var v = vertexList
    if (vertexList.length == 16) {
      gameboardString += v.head + "______" + v(1) + "______" + v(2) + "\n"
      gameboardString += "|      |      |\n"
      gameboardString += "|   " + v(3) + "__" + v(4) + "__" + v(5) + "   |\n"
      gameboardString += "|   |     |   |\n"
      gameboardString += v(6) + "___" + v(7) + "     " + v(8) + "___" + v(9) + "\n"
      gameboardString +=  "|   |     |   |\n"
      gameboardString += "|   " + v(10) + "__" + v(11) + "__" + v(12) + "   |\n"
      gameboardString += "|      |      |\n"
      gameboardString += v(13) + "______" + v(14) + "______" + v(15) + "\n"
    } else if (vertexList.length == 24) {
      gameboardString += v.head + "__________" + v(1) + "__________" + v(2) + "\n"
      gameboardString += "|          |          |\n"
      gameboardString += "|   " + v(3) + "______" + v(4) + "______" + v(5) + "   |\n"
      gameboardString += "|   |      |      |   |\n"
      gameboardString += "|   |   " + v(6) + "__" + v(7) + "__" + v(8) + "   |   |\n"
      gameboardString += "|   |   |     |   |   |\n"
      gameboardString += v(9) + "___" + v(10) + "___" + v(11) + "     " + v(12) + "___" + v(13) + "___" + v(14) + "\n"
      gameboardString += "|   |   |     |   |   |\n"
      gameboardString += "|   |   " + v(15) + "__" + v(16) + "__" + v(17) + "   |   |\n"
      gameboardString += "|   |      |      |   |\n"
      gameboardString += "|   " + v(18) + "______" + v(19) + "______" + v(20) + "   |\n"
      gameboardString += "|          |          |\n"
      gameboardString += v(21) + "__________" + v(22) + "__________" + v(23) + "\n"
    }
    gameboardString
  }
}
