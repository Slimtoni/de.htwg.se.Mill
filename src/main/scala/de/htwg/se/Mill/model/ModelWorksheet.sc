import de.htwg.se.Mill.model.{Field, FieldStatus}
import de.htwg.se.Mill.model.FieldStatus.Empty

import scala.collection.mutable
import de.htwg.se.Mill.model._


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

val list = gameboard.vertList()