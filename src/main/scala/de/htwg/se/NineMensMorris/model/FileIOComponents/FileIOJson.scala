package de.htwg.se.NineMensMorris.model.FileIOComponents

import de.htwg.se.NineMensMorris.controller.controllerComponent.Error
import de.htwg.se.NineMensMorris.model.FieldStatus.FieldStatus
import de.htwg.se.NineMensMorris.model.{FieldStatus, FileIOInterface, GameboardSize, PlayerGamePhase}
import de.htwg.se.NineMensMorris.model.gameboardComponent.GameboardInterface
import de.htwg.se.NineMensMorris.model.gameboardComponent.gameboardBaseImpl.{Field, Gameboard}
import de.htwg.se.NineMensMorris.model.playerComponent.PlayerInterface
import de.htwg.se.NineMensMorris.model.playerComponent.playerBaseImpl.Player
import play.api.libs.json._


import scala.collection.mutable
import scala.io.Source

class FileIOJson extends FileIOInterface {


  override def save(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): Unit = {

    import java.io._

    val pw = new PrintWriter(new File("Mill.json"))
    pw.write(Json.prettyPrint(millToJson(gameboard, player)))
    pw.close()
  }


  def load(): (GameboardInterface, (PlayerInterface, PlayerInterface, PlayerInterface)) = {
    var gameboard = new Gameboard()
    val file: String = Source.fromFile("mill.json").getLines().mkString
    val json: JsValue = Json.parse(file)


    val player1name = (json \ "Players" \ "player1" \ "name").get.toString().drop(1).dropRight(1).trim
    val player2name = (json \ "Players" \ "player2" \ "name").get.toString().drop(1).dropRight(1).trim
    val currentPlayerJson = (json \ "Players" \ "playerOnTurn" \ "name").get.toString().drop(1).dropRight(1).trim

    val player1phase = (json \ "Players" \ "player1" \ "phase").get.toString().drop(1).dropRight(1).trim match {
      case "Place" => PlayerGamePhase.Place
      case "Move" => PlayerGamePhase.Move
      case "Fly" => PlayerGamePhase.Fly
    }
    val player2phase = (json \ "Players" \ "player2" \ "phase").get.toString().drop(1).dropRight(1).trim match {
      case "Place" => PlayerGamePhase.Place
      case "Move" => PlayerGamePhase.Move
      case "Fly" => PlayerGamePhase.Fly
    }

    val player1PlacedMen = (json \ "Players" \ "player1" \ "placedMen").get.toString().toInt
    val player2PlacedMen = (json \ "Players" \ "player2" \ "placedMen").get.toString().toInt

    val player1LostMen = (json \ "Players" \ "player1" \ "lostMen").get.toString().toInt
    val player2LostMen = (json \ "Players" \ "player1" \ "lostMen").get.toString().toInt

    val player1 = Player(player1name, player1phase, player1PlacedMen, player1LostMen)
    val player2 = Player(player2name, player2phase, player2PlacedMen, player2LostMen)

    var currentPlayer: Player = null
    if (currentPlayerJson.equals(player1name)) {
      currentPlayer = player1
    } else {
      currentPlayer = player2
    }

    var vertexJson = (json \ "gameboard" \ "vertexList").get.toString().drop(1).dropRight(1).trim
    vertexJson = vertexJson.substring(12, vertexJson.length - 1)
    vertexJson = vertexJson.replaceAll(", ", "");
    for (x <- 0 until vertexJson.length) {
      var status: FieldStatus = null
      if (vertexJson.charAt(x) == 'O') {
        status = FieldStatus.Empty
      } else if (vertexJson.charAt(x) == 'W') {
        status = FieldStatus.White
      } else if (vertexJson.charAt(x) == 'B') {
        status = FieldStatus.Black
      } else {
        println("WTF")
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
    if (vertexJson.length == 24) {
      gameboard = gameboard.setEdgeList(GameboardSize.Nine, gameboard)
    }

    (gameboard, (player1, player2, currentPlayer))
  }


  def millToJson(gameboard: GameboardInterface, player: (PlayerInterface, PlayerInterface, PlayerInterface)): JsObject = {
    Json.obj(
      "Players" -> Json.obj(
        "player1" -> Json.obj(
          "name" -> JsString(player._1.name),
          "phase" -> JsString(player._1.phase.toString),
          "placedMen" -> JsNumber(player._1.numberPlacedMen),
          "lostMen" -> JsNumber(player._1.numberLostMen)

        ),
        "player2" -> Json.obj(
          "name" -> JsString(player._2.name),
          "phase" -> JsString(player._2.phase.toString),
          "placedMen" -> JsNumber(player._2.numberPlacedMen),
          "lostMen" -> JsNumber(player._2.numberLostMen)
        ),
        "playerOnTurn" -> Json.obj(
          "name" -> JsString(player._3.name)
        )
      ),
      "gameboard" -> Json.obj(
        "vertexList" -> JsString(gameboard.vertexList.toString())
      )
    )

  }
}