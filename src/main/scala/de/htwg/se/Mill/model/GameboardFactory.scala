package de.htwg.se.Mill.model

import de.htwg.se.Mill.model.GameboardSize.GameboardSize

object GameboardSize extends Enumeration {
  type GameboardSize = Value
  val Small, Medium, Large = Value
}

class GameboardFactory(size: GameboardSize) {

  def sz() : GameboardSize = size

  def createGameboard(size: GameboardSize): Gameboard[Field] = {
    size match {
      case GameboardSize.Small => {
        print("small")
        val gameboard = new Gameboard[Field]()
        gameboard
      };
      case GameboardSize.Medium => {
        print("medium")
        val gameboard = new Gameboard[Field]()
        gameboard
      };
      case GameboardSize.Large => {
        print("large")
        val gameboard = new Gameboard[Field]()
        gameboard
      };
    }
  }
}
