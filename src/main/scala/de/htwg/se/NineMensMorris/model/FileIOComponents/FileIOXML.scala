package de.htwg.se.NineMensMorris.model.FileIOComponents

import java.io.File

import de.htwg.se.NineMensMorris.controller.controllerComponent.Error
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.{FieldStatus, FileIOInterface, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.{Field, Gameboard}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player

import scala.collection.mutable

class FileIOXML extends FileIOInterface {


  def load(fileS: String): Option[(GameboardInterface, (PlayerInterface, PlayerInterface, PlayerInterface))] = {
    try {
      var gameboard = new Gameboard()


      val file = scala.xml.XML.loadFile(fileS)

      val player1name = (file \\ "player1" \\ "name").text.trim
      val player2name = (file \\ "player2" \\ "name").text.trim
      val playerOnTurnXML = (file \\ "playerOnTurn").text.trim

      val player1phase = (file \\ "player1" \\ "phase").text.trim match {
        case "Place" => PlayerGamePhase.Place
        case "Move" => PlayerGamePhase.Move
        case "Fly" => PlayerGamePhase.Fly
      }

      val player2phase = (file \\ "player2" \\ "phase").text.trim match {
        case "Place" => PlayerGamePhase.Place
        case "Move" => PlayerGamePhase.Move
        case "Fly" => PlayerGamePhase.Fly
      }

      val player1PlacedMen = (file \\ "player1" \\ "placedMen").text.trim.toInt
      val player2PlacedMen = (file \\ "player2" \\ "placedMen").text.trim.toInt

      val player1LostMen = (file \\ "player1" \\ "lostMen").text.trim.toInt
      val player2LostMen = (file \\ "player2" \\ "lostMen").text.trim.toInt


      val player1 = Player(player1name, player1phase, player1PlacedMen, player1LostMen)
      val player2 = Player(player2name, player2phase, player2PlacedMen, player2LostMen)
      var currentPlayer: PlayerInterface = null
      if (playerOnTurnXML.equals(player1.name)) {
        currentPlayer = player1
      } else {
        currentPlayer = player2
      }


      val vertexXml = (file \\ "vertexList").text.trim
      for (x <- 0 to vertexXml.length - 1) {
        var status: FieldStatus = null
        if (vertexXml.charAt(x) == 'O') {
          status = FieldStatus.Empty
        } else if (vertexXml.charAt(x) == 'W') {
          status = FieldStatus.White
        } else if (vertexXml.charAt(x) == 'B') {
          status = FieldStatus.Black
        }
        var tmpField = Field(x, status, mutable.MutableList.empty)
        gameboard.addVertex(tmpField)

      }


      val gameboardtmp = gameboard.setNeigh()
      gameboardtmp match {
        case Some(gmb) => {
          gameboard = gmb
        }
          Error.LoadError
      }
      if (vertexXml.length == 24) {
        gameboard = gameboard.setEdgeList(GameboardSize.Nine, gameboard)
      }
      println("load complete")

      Some(gameboard, (player1, player2, currentPlayer))
    } catch {
      case e: Exception => Error.LoadError
        None
    }

  }


  override def save(fileS: String, gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Error.Value = {
    try {
      val file = new File(fileS)
      scala.xml.XML.save(fileS, millToXML(gameboard, player))
      Error.NoError
    } catch {
      case e:Exception => Error.SaveError
    }

  }

  def millToXML(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)) = {
    <NineMensMorris>
      {gameboardToXML(gameboard)}{playerToXML(player)}
    </NineMensMorris>
  }


  def gameboardToXML(gameboard: GameboardInterface) = {
    <gameboard>
      <vertexList>
        {gameboard.vertexList}
      </vertexList>
    </gameboard>
  }


  def playerToXML(player: (PlayerInterface, PlayerInterface, PlayerInterface)) = {
    <players>
      <playerOnTurn>
        {player._3}
      </playerOnTurn>
      <player1>
        <name>
          {player._1.name}
        </name>
        <phase>
          {player._1.phase}
        </phase>
        <placedMen>
          {player._1.numberPlacedMen}
        </placedMen>
        <lostMen>
          {player._1.numberLostMen}
        </lostMen>
      </player1>
      <player2>
        <name>
          {player._2.name}
        </name>
        <phase>
          {player._2.phase}
        </phase>
        <placedMen>
          {player._2.numberPlacedMen}
        </placedMen>
        <lostMen>
          {player._2.numberLostMen}
        </lostMen>
      </player2>
    </players>

  }
}
