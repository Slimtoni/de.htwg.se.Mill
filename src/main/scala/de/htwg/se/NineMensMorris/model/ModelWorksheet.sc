import de.htwg.se.NineMensMorris.model.{Field, FieldStatus}
import de.htwg.se.NineMensMorris.model.FieldStatus.Empty

import scala.collection.mutable
import de.htwg.se.NineMensMorris.model._


val edge = Edge(Field(0, FieldStatus.White), Field(1, FieldStatus.Empty))

var list: mutable.MutableList[Edge[Field]] = mutable.MutableList()

list.+=(edge)

val edge2 = Edge(Field(0, FieldStatus.White), Field(1, FieldStatus.Empty))
list.contains(edge2)