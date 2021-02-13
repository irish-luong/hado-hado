organization := "max.helloworld"

name := "spark-app"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.apache.kafka" %% "kafka" % "2.1.0",
  "org.json4s" %% "json4s-native" % "3.7.0-M8",
  "com.typesafe" % "config" % "1.2.0"
)
