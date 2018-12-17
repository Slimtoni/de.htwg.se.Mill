package de.htwg.se.Mill.controller

import java.util.Observable
import scala.swing.Publisher

import de.htwg.se.Mill.model.{Field, Gameboard, GameboardFactory, GameboardSize}

class Controller(var gameboard: Gameboard[Field]) extends Publisher {
  var gameboardFactory = new GameboardFactory

  def gameboardToString: String = gameboard.toString

  def createGameboard(size: String): Unit = {
    /*if (size.equals("Large")) {
      gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    }
    else if (size.equals("Small")) {
      val gameboard = new GameboardFactory().createGameboard(GameboardSize.Small)
    }*/
    gameboard = gameboardFactory.createGameboard(GameboardSize.Large)
    publish(new FieldChanged)
  }



}