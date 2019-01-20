name := "NineMensMorris in Scala"
organization := "de.htwg.se"
version := "0.2.0"
scalaVersion := "2.12.7"
coverageEnabled := true

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.11.0"
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.1"
libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.1.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

coverageExcludedPackages := "de\\.htwg\\.se\\.NineMensMorris\\.a_view.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.NineMensMorris.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.Enumerations.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.gameboardComponent\\.gameboardBaseImpl\\.Gameboard.toString;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.gameboardComponent\\.gameboardMockImpl.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.playerComponent\\.playerMockImpl.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.controller\\.controllerComponent\\.controllerMockImpl.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.a_view.*;"+
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.gameboardComponent\\.gameboardMockImpl.*;"

test in assembly := {}
assemblyJarName in assembly := "NineMensMorris.jar"
mainClass in assembly := Some("NineMensMorris/NineMensMorris.")
