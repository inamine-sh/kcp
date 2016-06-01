name := """kcp"""

version := "1.0-SNAPSHOT"

// lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars" % "jquery-cookie" % "1.4.1-1",
  "org.webjars" % "datatables" % "1.10.11",
  "org.webjars" % "select2" % "3.5.2",
  "org.webjars" % "select2-bootstrap-css" % "1.4.6"
)
