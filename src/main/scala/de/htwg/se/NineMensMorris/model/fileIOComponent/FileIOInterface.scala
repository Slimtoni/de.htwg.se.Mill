package de.htwg.se.NineMensMorris.model.fileIOComponent

import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.playerComponent._
import de.htwg.se.NineMensMorris.controller.controllerComponent.Error

trait FileIOInterface {

  def save(fileS: String, gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Error.Value

  def load(fileS:String): Option[(GameboardInterface, (PlayerInterface,PlayerInterface,PlayerInterface))]

}
