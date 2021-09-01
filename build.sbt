name := "scala-pair-matcher"

version := "0.1"

scalaVersion := "2.13.6"

mainClass in (Compile, run) := Some("com.github.abryb.scalapairmatcher.Main")

libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
