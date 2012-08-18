libraryDependencies += "com.novocode" % "junit-interface" % "0.9-RC3" % "test"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
