package de.htwg.se.NineMensMorris.model.FileIOComponents

import de.htwg.se.NineMensMorris.controller.controllerComponent.controllerBaseImpl.ControllerMill
import de.htwg.se.NineMensMorris.model.{FieldStatus, GameboardSize, PlayerGamePhase}
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.NineMensMorris.model.fileIOComponent.fileIOXmlImpl.FileIO
import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.playerComponent._
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player
import de.htwg.se.NineMensMorris.controller.controllerComponent.Error

class FileIOXMLSpec extends WordSpec with Matchers {

  var file = new FileIO
  var playerWhite: PlayerInterface = new Player("playerWhite", PlayerGamePhase.Place, 0, 0)
  var playerBlack: PlayerInterface = new Player("playerBlack", PlayerGamePhase.Move, 0, 0)
  var playerOnTurn: PlayerInterface = _
  var gameboardFactory = new GameboardFactory
  var gameboard = gameboardFactory.createGameboard(GameboardSize.Nine)
  var players: (PlayerInterface, PlayerInterface) = _
  var controller = new ControllerMill(gameboardFactory.createGameboard(GameboardSize.Nine))
  gameboard.getField(7).changeFieldStatus(FieldStatus.Black)

  controller.createGameboard


  "A FileIO" when {
    "working" should {
      "save the game" in {
        file.save("test.xml", gameboard, (playerWhite, playerBlack, playerBlack)) should be(Error.NoError)


      }


      "load the game" in {
        val tmp = file.load("test.xml")

        tmp match {
          case None => Error.LoadError

          case Some(game) =>
            gameboard should be (gameboard)
            playerWhite should be (game._2._1)
            playerBlack should be (game._2._2)
            playerOnTurn should be (null)

        }

      }
    }

  }
}
