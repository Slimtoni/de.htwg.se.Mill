package de.htwg.se.NineMensMorris.model.playerComponent

import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase

trait PlayerInterface {
  def name: String
  def phase: PlayerGamePhase
  def numberPlacedMen: Int

  def checkedPlacedMen(): Option[PlayerInterface]
  def changeGamePhase(newPhase: PlayerGamePhase): PlayerInterface
  def incrementPlacedMen(): PlayerInterface
  def decrementPlacedMen(): PlayerInterface
  def equals(p: PlayerInterface): Boolean
}
