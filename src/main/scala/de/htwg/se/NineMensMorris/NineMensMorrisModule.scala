package de.htwg.se.NineMensMorris

import com.google.inject.AbstractModule
import de.htwg.se.NineMensMorris.model.FileIOInterface
import de.htwg.se.NineMensMorris.model.fileIOComponent._
import net.codingwell.scalaguice.ScalaModule

class NineMensMorrisModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[FileIOInterface].to[fileIOJsonImpl.FileIO]
  }
}