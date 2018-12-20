package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.EdgeDirection.EdgeDirection
import scala.collection.mutable
import scala.language.postfixOps

/**
  * class of the Gameboard
  * @param size is the size of the Gameboard
  * @tparam Field is the type ofgit the Graph-Attribute
  */
case class Gameboard(vertexList: mutable.MutableList[Field], neigh: mutable.MutableList[Edge[Field]])
  extends Graph[Field] {

  //def this(size: Int) = this(new mutable.MutableList[Field], new mutable.MutableList[(Field, Field)]){}
  def vertList(): mutable.MutableList[Field] = vertexList
  def nbourList(): mutable.MutableList[Edge[Field]] = neigh

  override def addVertex(v: Field): Gameboard = {
    if (!vertexList.contains(v)) vertexList.+=(v)
    copy(vertexList, neigh)
  }

  override def addEdge(v: Field, w: Field, direc : EdgeDirection): Gameboard = {
    if (!containsVertex(v)) addVertex(v)
    if (!containsVertex(w)) addVertex(w)
    if (!containsEdge(v,w) || !containsEdge(w,v)) {
      val edge = new Edge[Field](v,w,direc)
      neigh.+=(edge)
    }
    copy(vertexList, neigh)
  }

  override def containsVertex(v: Field): Boolean = {
    for (i<-this.vertexList) {
      if (v.equals(i)) return true
    }
    false
  }

  override def containsEdge(v: Field, w: Field): Boolean = {
    if (!containsVertex(v) || !containsVertex(w)) {
      throw new IllegalArgumentException(v + " or "+ w + " isn`t a Vertex")
    }
    neigh.contains((v, w)) || neigh.contains((v, w))
  }

  def set(field: Int, fieldStatus: String): Option[Gameboard] = {
    val fieldtoChange: Option[Field] = vertexList.get(field)
    fieldtoChange match {
      case Some(f) => {
        if (f.fieldStatus == FieldStatus.Empty) {
          fieldStatus match {
            case "Black" => vertexList(field) = f.changeFieldStatus(FieldStatus.Black)
            case "White" => vertexList(field) = f.changeFieldStatus(FieldStatus.White)
            case "Empty" =>
            case _ =>       println("Unknown Fieldstatus")
          }
        } else {
          return None
        }

      }
      case None => println("Field " + field + " not found on this Gameboard!")
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
/*
object Gameboard {
  private val _instance = new Gameboard[Field]
  def instance(): Gameboard[Field] = _instance
}*/