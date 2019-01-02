name          := "NineMensMorris in Scala"
organization  := "de.htwg.se"
version       := "0.2.0"
scalaVersion  := "2.12.7"
coverageEnabled := true


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
//libraryDependencies += "org.scalamock" %% "scalamock" % "3.2.2" % "test"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

coverageExcludedPackages := "de\\.htwg\\.se\\.NineMensMorris\\.a_view.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.NineMensMorris.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.Enumerations.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.gameboardComponent\\.gameboardBaseImpl\\.Gameboard.toString;"