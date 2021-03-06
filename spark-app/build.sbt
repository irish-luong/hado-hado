organization := "max.helloworld"

name := "spark-app"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % "3.7.0-M8",
  "com.typesafe" % "config" % "1.2.0",
  "org.apache.spark" %% "spark-sql" % "3.1.0",
  "org.apache.kafka" % "kafka-clients" % "2.7.0",
  "org.apache.spark" % "spark-sql-kafka-0-10_2.12" % "3.0.1" % "provided",
  "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  "org.scala-js" %% "scalajs-test-interface" % "0.6.14",
  "org.scalatest" %% "scalatest" % "3.0.1",
  "com.novocode" % "junit-interface" % "0.11"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF","services",xs @ _*) => MergeStrategy.filterDistinctLines
  case PathList("META-INF",xs @ _*) => MergeStrategy.discard
  case "application.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}
