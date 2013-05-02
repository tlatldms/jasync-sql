import sbt._
import Keys._

object ProjectBuild extends Build {


  lazy val root = Project(
    id = "db-async-base",
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      scalaVersion := "2.10.1",
      autoScalaLibrary := true
    ),
    aggregate = Seq(common, postgresql)
  )

  lazy val common = Project(
    id = "db-async-common",
    base = file("db-async-common"),
    settings = Defaults.defaultSettings ++ Seq(
      name := "db-async-common",
      version := "0.1.2-SNAPSHOT",
      scalaVersion := "2.10.1",
      libraryDependencies := Configuration.commonDependencies,
      autoScalaLibrary := true
    )
  )

  lazy val postgresql = Project(
    id = "postgresql-async",
    base = file("postgresql-async"),
    settings = Defaults.defaultSettings ++ Seq(
      name := "postgresql-async",
      version := "0.1.2-SNAPSHOT",
      scalaVersion := "2.10.1",
      libraryDependencies ++= Configuration.postgresqlAsyncDependencies,
      autoScalaLibrary := true
    )
  ) aggregate(common) dependsOn(common)

}

object Configuration {

  val specs2Dependency = "org.specs2" %% "specs2" % "1.14" % "test"

  val commonDependencies =  Seq(
    "commons-pool" % "commons-pool" % "1.6",
    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "joda-time" % "joda-time" % "2.2",
    "org.joda" % "joda-convert" % "1.3.1",
    specs2Dependency
  )

  val postgresqlAsyncDependencies = Seq(
    "io.netty" % "netty" % "3.6.5.Final",
    specs2Dependency
  )

}