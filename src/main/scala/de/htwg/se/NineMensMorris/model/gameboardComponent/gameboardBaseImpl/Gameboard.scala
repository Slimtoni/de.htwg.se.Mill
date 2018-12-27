package de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl

import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent.{EdgeInterface, FieldInterface, GameboardInterface}

import scala.collection.mutable

case class Gameboard(vertexList: mutable.MutableList[FieldInterface], neigh: mutable.MutableList[EdgeInterface]) extends GameboardInterface {

  def this() = this(mutable.MutableList[Field], mutable.MutableList[Edge])

  def getField(id: Int): FieldInterface = {
    for (i <- vertexList.iterator) if (i.id == id) return i
    Field(99,FieldStatus.Empty) // error case with dummy Field
  }

  def addVertex(v: FieldInterface): GameboardInterface = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  def addEdge(v: FieldInterface, w: FieldInterface): GameboardInterface = {
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

  def set(field: Int, fieldStatus: String): Option[GameboardInterface] = {
    val fieldtoChange: Option[FieldInterface] = vertexList.get(field)
    fieldtoChange match {
      case Some(f) => {
          fieldStatus match {
            case "Black" => vertexList(field) = f.changeFieldStatus(FieldStatus.Black)
            case "White" => vertexList(field) = f.changeFieldStatus(FieldStatus.White)
            case "Empty" => vertexList(field) = f.changeFieldStatus(FieldStatus.Empty)
            case _ =>       println("Unknown Fieldstatus")
          }
      }
      case None => return None
    }
    Option(copy(vertexList, neigh))
  }

  override def toString: String = {
    var gameboardString: String = ""
    var v = vertexList
    if (vertexList.length == 8) {
      gameboardString += v.head + "__" + v(1) + "__" + v(2) + "\n"
      gameboardString += "|     |\n"
      gameboardString += v(3) + "     " + v(4) + "\n"
      gameboardString += "|     |\n"
      gameboardString += v(5) + "__" + v(6) + "__" + v(7) + "\n"
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
