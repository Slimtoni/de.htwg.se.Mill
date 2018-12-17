package de.htwg.se.Mill.controller

import java.util.Observable

import de.htwg.se.Mill.model.GameboardSize.GameboardSize
import de.htwg.se.Mill.model.{Field, Gameboard, GameboardFactory, GameboardSize}

class Controller extends Observable {

  def createGameboard(size: GameboardSize): Unit = {
    if (size.equals("Large")) {
      val gameboard = new GameboardFactory().createGameboard(GameboardSize.Large)
      notifyObservers()
    }
    else if (size.equals("Small")) {
      val gameboard = new GameboardFactory().createGameboard(GameboardSize.Small)
      notifyObservers()
    }

  }

}