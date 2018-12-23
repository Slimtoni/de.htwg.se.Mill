package de.htwg.se.NineMensMorris.controller.impl

import scala.swing.event.Event

class FieldChanged extends Event

class GamePhaseChanged extends Event

class PlayerPhaseChanged extends Event

class CurrentPlayerChanged extends Event

class Error(msg: String) extends Event

class InvalidFieldError extends Event

class MissingEdgeError extends Event