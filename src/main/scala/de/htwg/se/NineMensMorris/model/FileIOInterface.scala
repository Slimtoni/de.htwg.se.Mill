package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.playerComponent._

trait FileIOInterface {

  //def load(): Option[GameboardInterface]

  def save(fileS: String, gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Unit

  def load(fileS:String): Option[(GameboardInterface, (PlayerInterface,PlayerInterface,PlayerInterface))]

}
