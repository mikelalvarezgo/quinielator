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
