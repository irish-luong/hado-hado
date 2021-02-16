package spark_jobs

import util.ConfigUtil
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{Strucfield, _}

/*
Object implement a spark job consume a Kafka topic
the makes an aggregation operation then return a dataframe
 */
object NetworkStreamingJob extends App {

  // Load configuration from Singleton object
  val env: String = "dev"
  val config = ConfigUtil.getConfig(env)

  // Start a Spark Session to a running spark cluster
  val spark: SparkSession = SparkSession.builder
    .master(config.getString("spark.master"))
    .appName(getClass.getSimpleName)
    .getOrCreate()

  import spark.implicits._

  val schema = Strucfield(
    List(

    )
  )



}