package de.htwg.se.NineMensMorris.model.playerComponent.playerMockImpl

import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

class Player extends PlayerInterface {
  override def name: String = "White"

  override def phase: PlayerGamePhase = PlayerGamePhase.Place

  override def numberPlacedMen: Int = 0

  override def checkedPlacedMen(): Option[PlayerInterface] = Some(this)

  override def changeGamePhase(newPhase: PlayerGamePhase): PlayerInterface = this

  override def incrementPlacedMen(): PlayerInterface = this

  override def decrementPlacedMen(): PlayerInterface = this

  override def equals(p: PlayerInterface): Boolean = true
}
