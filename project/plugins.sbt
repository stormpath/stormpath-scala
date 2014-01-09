// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % Option(System.getProperty("play.version")).getOrElse("2.2.1"))

addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.1")