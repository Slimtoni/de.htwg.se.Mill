package de.htwg.se.NineMensMorris.a_view.Gui

import java.awt.Color

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface

import scala.swing.{Alignment, BorderPanel, FlowPanel, GridPanel, Label}

class StatusPanel(controller: ControllerInterface) extends BorderPanel {
  var message = new Label("Welcome to NineMensMill!")
  var info = new Label("Game started!")
  val currentPlayerLabel = new Label("Current Player: ")
  val playerPhaseLabel = new Label("Current Players Gamephase: ")
  val backgroundColor =  new Color(255, 255, 102)


  val infoPanel: FlowPanel = new FlowPanel {
    contents += message
    contents += info
    background = backgroundColor
  }

  val statusPanel: GridPanel = new GridPanel(1,2) {
    currentPlayerLabel.horizontalAlignment = Alignment.Left
    playerPhaseLabel.horizontalAlignment = Alignment.Right
    contents += currentPlayerLabel
    contents += playerPhaseLabel
    background = backgroundColor
  }

  def setMessage(msg: String): Unit = {
    message.text = msg
  }
  def setInfo(msg: String): Unit = {
    info.text = msg
  }

  def refresh(): Unit = {
    currentPlayerLabel.text = "Current Player: " + controller.getPlayerOnTurn
    playerPhaseLabel.text = "Current Players Gamephase: " + controller.getPlayerOnTurnPhase
    message.text = controller.getPlayerOnTurn + " Player has to " + controller.getPlayerOnTurnPhase + " a Man!"
    revalidate()
    repaint()
  }

  add(infoPanel, BorderPanel.Position.North)
  add(statusPanel, BorderPanel.Position.South)
}