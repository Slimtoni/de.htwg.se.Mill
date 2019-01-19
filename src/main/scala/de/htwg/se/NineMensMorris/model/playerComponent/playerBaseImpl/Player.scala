package de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl

import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

case class Player(name: String, phase: PlayerGamePhase, var numberPlacedMen: Int, var numberLostMen: Int) extends PlayerInterface {

  override def checkedPlacedMen(): Option[PlayerInterface] = {
    if (numberPlacedMen == 4) {
      if (numberLostMen < 1) return Some(changeGamePhase(PlayerGamePhase.Move))
      else if (numberLostMen >= 1) return Some(changeGamePhase(PlayerGamePhase.Fly))
    }
    Some(this)
  }

  override def checkPlayerLost(): Boolean = {
    if (numberLostMen >= 2) true
    else false
  }

  def changeGamePhase(newPhase: PlayerGamePhase): Player = copy(name, newPhase, numberPlacedMen, numberLostMen)

  def incrementPlacedMen(): Player = {
    numberPlacedMen += 1
    copy(name, phase, numberPlacedMen, numberLostMen)
  }

  def incrementLostMen(): Player = {
    numberLostMen += 1
    copy(name, phase, numberPlacedMen, numberLostMen)
  }

  def equals(o: PlayerInterface): Boolean = {
    if (o.name == this.name) true
    else false
  }

  override def toString: String = name
}


