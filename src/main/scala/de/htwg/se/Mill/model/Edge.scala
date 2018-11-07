package de.htwg.se.Mill.model

class Edge[V](source: V, target: V, direction: Boolean) {

  override def toString: String = {
    if (direction) "__"
    else "|"
  }
}
