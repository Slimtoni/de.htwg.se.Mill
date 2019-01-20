package de.htwg.se.NineMensMorris.model.fileIOComponent

import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardInterface
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

trait FileIOInterface {

  //def load(): Option[GameboardInterface]

  def save(fileS: String, gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Unit

  def load(fileS:String): Option[(GameboardInterface, (PlayerInterface,PlayerInterface,PlayerInterface))]

}
