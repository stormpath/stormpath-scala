name := "stormpath-scala"

version := "1.0"

scalaVersion := "2.10.1"

libraryDependencies ++= Seq(
  "com.stormpath.sdk" % "stormpath-sdk-api" % "0.8.1"
)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"
