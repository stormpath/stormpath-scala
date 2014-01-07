organization := "com.stormpath.scala"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
    "play-plugin-releases" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"
)

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"