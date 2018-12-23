package de.htwg.se.NineMensMorris.model

import de.htwg.se.NineMensMorris.model.PlayerGamePhase.PlayerGamePhase

case class Player(name: String,var phase: PlayerGamePhase = PlayerGamePhase.Place,var numberPlacedMen: Int) {

  var numberLostMen: Int = 0

  def checkPlacedMen(): Boolean = {
    if (numberPlacedMen <= 9 && numberLostMen <= 6) {
      changeGamePhase(PlayerGamePhase.Move)
    } else if (numberPlacedMen <= 9 && numberLostMen > 6) {
      changeGamePhase(PlayerGamePhase.Fly)
    } else if (numberLostMen == 7) {
      return false
    }
    true
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



  override def toString: String = name
}

