name          := "Mill in Scala"
organization  := "de.htwg.se"
version       := "0.2.0"
scalaVersion  := "2.12.7"
coverageEnabled := true

libraryDependencies ++= {
  val scalaTestV = "3.0.1"
  val scalaMockV = "3.2.2"
  Seq(
    "org.scalatest" %% "scalatest" % scalaTestV % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV % "test"
  )
}

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

coverageExcludedPackages := "de\\.htwg\\.se\\.NineMensMorris\\.a_view.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.NineMensMorris.*;" +
  "de\\.htwg\\.se\\.NineMensMorris\\.model\\.Enumerations;"