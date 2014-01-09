organization := "com.stormpath.scala"

version := "0.1.0-SNAPSHOT"

resolvers ++= Seq(
    "play-plugin-releases" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

publishTo := Some(Resolver.file("file", file(Path.userHome.absolutePath+"/.m2/repository")))