name := "chromed-scala"
organization := "com.dealermade"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  ws,
  "com.beachape" %% "enumeratum" % "1.5.12",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test
)

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    // if Scala 2.11+ is used, use scala-xml 1.x
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value ++ Seq("org.scala-lang.modules" %% "scala-xml" % "1.0.6")
    case _ => libraryDependencies.value
  }
}

enablePlugins(PlayScala)