package de.htwg.se.NineMensMorris.a_view.Gui

import de.htwg.se.NineMensMorris.controller.controllerComponent.ControllerInterface

import scala.swing.{Alignment, BorderPanel, FlowPanel, GridPanel, Label}

class StatusPanel(controller: ControllerInterface) extends BorderPanel {
  var message = new Label("N/A")
  val currentPlayerLabel = new Label("N/A")
  val playerPhaseLabel = new Label("N/A")

  val infoPanel: FlowPanel = new FlowPanel {
    contents += message
  }

  val statusPanel: GridPanel = new GridPanel(1,2) {
    currentPlayerLabel.horizontalAlignment = Alignment.Left
    playerPhaseLabel.horizontalAlignment = Alignment.Right
    contents += currentPlayerLabel
    contents += playerPhaseLabel
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
