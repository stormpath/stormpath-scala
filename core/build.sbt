organization := "com.stormpath.scala"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
    "com.stormpath.sdk" % "stormpath-sdk-api" % "0.9.0"
)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishTo := Some(Resolver.file("file", file(Path.userHome.absolutePath+"/.m2/repository")))