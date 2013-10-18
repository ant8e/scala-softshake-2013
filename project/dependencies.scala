import sbt._

object Dependencies {

  val h2Version = "1.3.173"

  val resolvers = Seq(
    "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
    "spray repo" at "http://repo.spray.io/",
    "spray on the edge" at "http://nightlies.spray.io")


  val h2 = "com.h2database" % "h2" % h2Version
  val slickVersion = "1.0.1"
  val slick = "com.typesafe.slick" %% "slick" % slickVersion
  val slf4j = "org.slf4j" % "slf4j-nop" % "1.6.4"
  val db = Seq(h2, slick, slf4j)
  val vaadin = Seq("vaadin.scala" %% "scaladin" % "3.0-SNAPSHOT", "com.vaadin" % "vaadin-client-compiled" % "7.1.7","com.vaadin" % "vaadin-themes" % "7.1.7",
    "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container")

  val deps = db ++ vaadin

}