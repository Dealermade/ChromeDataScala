name := "chromed-scala"
organization := "com.dealermade"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.6.2" % "provided",
  "com.google.inject" % "guice" % "4.1.0" % "provided"
)