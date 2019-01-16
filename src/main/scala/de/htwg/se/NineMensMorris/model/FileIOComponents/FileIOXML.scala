package de.htwg.se.NineMensMorris.model.FileIOComponents

import java.io.File

import de.htwg.se.NineMensMorris.model.gameboardComponent._
import de.htwg.se.NineMensMorris.model.FileIOInterface
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface

import scala.collection.JavaConverters._
import scala.xml.PrettyPrinter

class FileIOXML extends FileIOInterface {


  override def load(): Option[(GameboardInterface, (PlayerInterface, PlayerInterface))] = {
    var gameboardOption: Option[GameboardInterface] = None
    val source = new File("mill.xml")
    if (source.exists()) {
      val file = scala.xml.XML.loadFile("mill.xml")


    }
  }

  override def save(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface)): Unit = {
    scala.xml.XML.save("mill.xml", millToXML(gameboard, player))
  }


  def millToXML(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface)) = {
    <NineMensMorris>
      {gameboardToXML(gameboard)}{playerToXML(player)}
    </NineMensMorris>
  }


  def gameboardToXML(gameboard: GameboardInterface): Unit = {
    <gameboard>
      <vertexList>gameboard.vertexList</vertexList>
      <neighList>gameboard.neigh</neighList>
    </gameboard>
  }

  def playerToXML(player: (PlayerInterface, PlayerInterface)): Unit = {
    <players>
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
