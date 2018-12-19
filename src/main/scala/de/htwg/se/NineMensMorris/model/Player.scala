package de.htwg.se.NineMensMorris.model

case class Player(name: String, phase: Int) {
  override def toString: String = name
}

