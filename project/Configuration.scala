import org.scalafmt.sbt.ScalafmtPlugin.autoImport._
import sbt._
import sbt.Keys._
import sbt.nio.Keys._

object Configuration {
  lazy val tb = taskKey[Unit]("Launch behaviour tests")
  lazy val ti = taskKey[Unit]("Launch integration tests")
  lazy val ta = taskKey[Unit]("Launch acceptance tests")

  val commonSettings = Seq(
    organization := "com.mikelalvarezgo",
    version := "1.0.0",
    scalaVersion := "2.13.6",
    Compile / doc / sources := Seq.empty,
    Compile / packageDoc / publishArtifact := false,
    scalacOptions := {
      val default = Seq(
        "-Xfatal-warnings",
        "-Ywarn-unused",
        "-Ywarn-value-discard",
        "-language:higherKinds",
        "-language:implicitConversions",
        "-Wconf:cat=deprecation:w",
        "-Wconf:cat=lint:w,cat=lint-byname-implicit:w",
        "-Wconf:cat=feature:i",
        "-Wconf:cat=unchecked:w",
        "-Wconf:cat=unused:w",
        "-Wconf:cat=w-flag-dead-code:w,cat=w-flag-value-discard:w"
      )
      if (version.value.endsWith("SNAPSHOT")) default :+ "-Xcheckinit" else default
      // check against early initialization
    },
    javaOptions := Seq(
      "-Duser.timezone=UTC",
      "-Xmx2048M",
      "-Xss2M"),
    run / fork := true,
    Test / fork := true,
    Test / parallelExecution := false,
    tb := (Test / testOnly).toTask(s" **.behaviour.**").value,
    ti := (Test / testOnly).toTask(s" **.integration.**").value,
    ta := (Test / testOnly).toTask(s" **.acceptance.**").value,
    Test / testOptions ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      Tests.Argument("-oDF")
    ),
    Global / cancelable := true,
    Global / onChangedBuildSource := ReloadOnSourceChanges,
    // Scalafmt
    scalafmtConfig := file(".scalafmt.conf"),
    // Cache
    pushRemoteCacheTo := Some(MavenCache("compile-local-cache", file(sys.env("HOME") + "/.sbt/letgo-cache/"))),
    Compile / pushRemoteCacheConfiguration ~= (_.withOverwrite(true)),
    Test / pushRemoteCacheConfiguration ~= (_.withOverwrite(true))
  )

}
