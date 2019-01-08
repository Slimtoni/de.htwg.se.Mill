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
    Field(99, FieldStatus.Empty, mutable.MutableList.empty) // error case with dummy Field
  }

  def addVertex(v: FieldInterface): Gameboard = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  def addEdge(v: FieldInterface, w: FieldInterface): Gameboard = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (!containsEdge(v, w) || !containsEdge(w, v)) {
      val edge = Edge(v, w)
      neigh.+=(edge)
    }
    copy(vertexList, neigh)
  }

  def containsVertex(v: FieldInterface): Boolean = {
    for (i <- this.vertexList) {
      if (v.checkID(i)) return true
    }
    false
  }

  def containsEdge(v: FieldInterface, w: FieldInterface): Boolean = {
    if (containsVertex(v) && containsVertex(w)) {
      val edge = Edge(v, w)
      for (i <- this.neigh) {
        //print(i)
        if (edge.equals(i)) return true
      }
    }
    false
  }


  def setNeigh(): Option[Gameboard] = {
    val v = vertexList
    v.update(0, v(0).changeMillNeigh(mutable.MutableList((v(1), v(2)), (v(9), v(21)))))
    v.update(1, v(1).changeMillNeigh(mutable.MutableList((v(0), v(2)), (v(4), v(7)))))
    v.update(2, v(2).changeMillNeigh(mutable.MutableList((v(0), v(1)), (v(14), v(23)))))
    v.update(3, v(3).changeMillNeigh(mutable.MutableList((v(4), v(5)), (v(13), v(20)))))
    v.update(4, v(4).changeMillNeigh(mutable.MutableList((v(3), v(5)), (v(1), v(7)))))
    v.update(5, v(5).changeMillNeigh(mutable.MutableList((v(3), v(4)), (v(14), v(23)))))
    v.update(6, v(6).changeMillNeigh(mutable.MutableList((v(7), v(8)), (v(11), v(15)))))
    v.update(7, v(7).changeMillNeigh(mutable.MutableList((v(6), v(8)), (v(1), v(4)))))
    v.update(8, v(8).changeMillNeigh(mutable.MutableList((v(6), v(8)), (v(12), v(17)))))
    v.update(9, v(9).changeMillNeigh(mutable.MutableList((v(10), v(11)), (v(0), v(21)))))
    v.update(10, v(10).changeMillNeigh(mutable.MutableList((v(9), v(11)), (v(3), v(18)))))
    v.update(11, v(11).changeMillNeigh(mutable.MutableList((v(9), v(10)), (v(6), v(15)))))
    v.update(12, v(12).changeMillNeigh(mutable.MutableList((v(13), v(14)), (v(8), v(17)))))
    v.update(13, v(13).changeMillNeigh(mutable.MutableList((v(12), v(14)), (v(5), v(20)))))
    v.update(14, v(14).changeMillNeigh(mutable.MutableList((v(12), v(13)), (v(2), v(23)))))
    v.update(15, v(15).changeMillNeigh(mutable.MutableList((v(16), v(17)), (v(6), v(11)))))
    v.update(16, v(16).changeMillNeigh(mutable.MutableList((v(15), v(17)), (v(19), v(22)))))
    v.update(17, v(17).changeMillNeigh(mutable.MutableList((v(15), v(16)), (v(8), v(12)))))
    v.update(18, v(18).changeMillNeigh(mutable.MutableList((v(19), v(20)), (v(3), v(10)))))
    v.update(19, v(19).changeMillNeigh(mutable.MutableList((v(18), v(20)), (v(16), v(22)))))
    v.update(20, v(20).changeMillNeigh(mutable.MutableList((v(18), v(19)), (v(5), v(13)))))
    v.update(21, v(21).changeMillNeigh(mutable.MutableList((v(22), v(23)), (v(0), v(9)))))
    v.update(22, v(22).changeMillNeigh(mutable.MutableList((v(21), v(23)), (v(16), v(19)))))
    v.update(23, v(23).changeMillNeigh(mutable.MutableList((v(21), v(22)), (v(2), v(14)))))


    Option(copy(v,neigh))

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
