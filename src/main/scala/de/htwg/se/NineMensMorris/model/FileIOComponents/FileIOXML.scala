package de.htwg.se.NineMensMorris.model.FileIOComponents

import java.io.File

import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.FileIOInterface
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

import scala.collection.JavaConverters._
import scala.xml.PrettyPrinter

class FileIOXML extends FileIOInterface {


   /*override def load(): Option[(GameboardInterface, (PlayerInterface, PlayerInterface, PlayerInterface))] = {
     var gameboardOption: Option[GameboardInterface] = None
     val source = new File("mill.xml")
     if (source.exists()) {
       val file = scala.xml.XML.loadFile("mill.xml")
       val player1name = (file \\ "player1 @name").text.trim
       val player2name = (file \\ "player2 @name").text.trim

       val player1: PlayerInterface



     }

     Some(gameboard, (player1, player1))
   }*/


  override def save(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Unit = {
    val file = new File("mill.xml")
    scala.xml.XML.save("mill.xml", millToXML(gameboard, player))
    println("saved file")
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
      </player2>
    </players>

  }
}
