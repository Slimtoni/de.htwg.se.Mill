package de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl

import de.htwg.se.NineMensMorris.model.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

case class Player(name: String, phase: PlayerGamePhase, var numberPlacedMen: Int) extends PlayerInterface {

  var numberLostMen: Int = 0

  override def checkedPlacedMen(): Option[PlayerInterface] = {
    if (numberPlacedMen >= 1 && numberLostMen <= 6) {
      return Some(changeGamePhase(PlayerGamePhase.Move))
    } else if (numberPlacedMen <= 9 && numberLostMen > 6) {
      return Some(changeGamePhase(PlayerGamePhase.Fly))
    } else if (numberLostMen == 7) {
      return None
    }
    Some(this)
  }

  def changeGamePhase(newPhase: PlayerGamePhase): Player = copy(name, newPhase, numberPlacedMen)

  def incrementPlacedMen(): Player = {
    numberPlacedMen += 1
    copy(name, phase, numberPlacedMen)
  }

  def decrementPlacedMen(): Player = {
    numberPlacedMen -= 1
    copy(name, phase, numberPlacedMen)
  }

  def equals(o: PlayerInterface): Boolean = {
    if (o.name == this.name) true
    else false
  }

  override def toString: String = name


}
