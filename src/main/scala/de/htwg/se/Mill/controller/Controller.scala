package de.htwg.se.Mill.controller

import java.util.Observable

import de.htwg.se.Mill.model.{Field, Gameboard, GameboardFactory, GameboardSize}

class Controller(var gameboard: Gameboard[Field]) extends Observable {
  var gameboardFactory = new GameboardFactory

  def createGameboard(size: String): Unit = {
    /*if (size.equals("Large")) {
      gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    }
    else if (size.equals("Small")) {
      val gameboard = new GameboardFactory().createGameboard(GameboardSize.Small)
    }*/
    gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    println(gameboard)
    notifyObservers()
  }

  def gameboardToString: String = gameboard.toString


}