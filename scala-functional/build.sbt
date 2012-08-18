name := "hello"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// disable using the Scala version in output paths and artifacts
crossPaths := false

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

externalPom()
