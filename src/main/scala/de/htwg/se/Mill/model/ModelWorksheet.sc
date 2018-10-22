import de.htwg.se.Mill.model.{Field, FieldStatus}
import de.htwg.se.Mill.model.FieldStatus.Empty

import scala.collection.mutable

case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

import de.htwg.se.Mill.model.Gameboard

val gameboard = new Gameboard[Field](4)
val field1 = new Field(FieldStatus.Empty)
val field2 = new Field(FieldStatus.Empty)
val field3 = new Field(FieldStatus.Empty)
val field4 = new Field(FieldStatus.Empty)

gameboard.addVertex(field1)
gameboard.addVertex(field1)
gameboard.addVertex(field2)

gameboard.addEdge(field1,field2)
gameboard.addEdge(field1,field2)

val i = gameboard.getSize()