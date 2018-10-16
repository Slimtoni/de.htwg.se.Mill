package de.htwg.se.Mill.model

class Edge[V](source: V, target: V) {

  def src() = source
  def tar() = target

  override def toString: String = "src:" + source.toString + " - tar:" + target.toString
}
