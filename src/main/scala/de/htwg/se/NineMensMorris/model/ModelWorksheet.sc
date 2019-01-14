import de.htwg.se.NineMensMorris.model.FieldStatus
import de.htwg.se.NineMensMorris.model.FieldStatus.Empty

import scala.collection.mutable
import de.htwg.se.NineMensMorris.model._
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.{Edge, Field, Gameboard}

var gameboard = new Gameboard()
val field1 = new Field(1)
gameboard = gameboard.addVertex(field1)
