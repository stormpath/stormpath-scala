organization := "com.stormpath.scala"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
    "com.stormpath.sdk" % "stormpath-sdk-api" % "0.9.0"
)

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))