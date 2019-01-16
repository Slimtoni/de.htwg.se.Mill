package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.playerComponent._

trait FileIOInterface {

  def load(): Option[GameboardInterface]

  def save(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface)): Unit

}
