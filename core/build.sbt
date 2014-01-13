organization := "com.stormpath.scala"

version := "0.1.0-SNAPSHOT"

libraryDependencies ++= Seq(
    "com.stormpath.sdk" % "stormpath-sdk-api" % "0.9.0"
)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

pomExtra := (
  <url>https://github.com/stormpath/stormpath-scala</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:stormpath/stormpath-scala.git</url>
    <connection>scm:git:git@github.com:stormpath/stormpath-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>lhazlewood</id>
      <name>Les Hazlewood</name>
      <email>les@stormpath.com</email>
      <url>http://www.leshazlewood.com</url>
      <timezone>-8</timezone>
      <organization>Stormpath, Inc.</organization>
      <organizationUrl>http://www.stormpath.com</organizationUrl>
    </developer>
    <developer>
      <id>antollinim</id>
      <name>Mario Antollini</name>
      <email>mario@stormpath.com</email>
      <timezone>-3</timezone>
      <organization>Stormpath, Inc.</organization>
      <organizationUrl>http://www.stormpath.com</organizationUrl>
    </developer>
  </developers>)

publishTo := Some(Resolver.file("file", file(Path.userHome.absolutePath+"/.m2/repository")))
