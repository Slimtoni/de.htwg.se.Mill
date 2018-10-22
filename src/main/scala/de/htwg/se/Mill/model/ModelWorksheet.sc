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

gameboard.addEdge(field1, field2)
gameboard.addEdge(field2, field3)
gameboard.addEdge(field3, field4)
gameboard.addEdge(field4, field1)
gameboard.addEdge(field4, field1)
gameboard.addEdge(field1, field4)
