name := "quinielator"

version := "0.1"

scalaVersion := "2.13.7"

lazy val root = project
  .in(file("."))
  .settings(Configuration.commonSettings)
  .aggregate(
    shared,
    odds_checker
  )

lazy val shared = project
  .disablePlugins(AssemblyPlugin)
  .settings(
    name := "shared",
    Configuration.commonSettings,
    libraryDependencies ++= Dependencies.commonDependencies
  )

lazy val odds_checker = project
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "odds_checker",
    Configuration.commonSettings,
    libraryDependencies ++= (Dependencies.commonDependencies)
  )
  .dependsOn(shared % "compile->compile;test->test")


addCommandAlias("c", "compile")
addCommandAlias("tc", "Test/compile")
addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("f", "scalafmt")
addCommandAlias("ft", "scalafmtCheck")
addCommandAlias("tf", "Test/scalafmt")
addCommandAlias("tft", "Test/scalafmtCheck")
addCommandAlias("s", "scalastyle")
addCommandAlias("ts", "Test/scalastyle")
addCommandAlias("dc", "dependencyCheck")

addCommandAlias("ca", ";c;tc")
addCommandAlias("sc", ";s;ts")
addCommandAlias("fc", ";ft;tft")
addCommandAlias("prep", ";ca;sc;fc;dc")