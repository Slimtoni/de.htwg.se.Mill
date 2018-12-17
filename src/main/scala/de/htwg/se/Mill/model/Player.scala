package de.htwg.se.Mill.model

case class Player(name: String, phase: Int) {
  override def toString: String = name
}

