name := "hello"

version := "0.1-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

libraryDependencies += "com.novocode" % "junit-interface" % "0.8" % "test->default"

libraryDependencies += "no.knowit" % "common" % "0.1-SNAPSHOT"

libraryDependencies += "org.joda" % "joda-convert" % "1.2"

libraryDependencies += "org.hamcrest" % "hamcrest-all" % "1.1"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.9.2"
