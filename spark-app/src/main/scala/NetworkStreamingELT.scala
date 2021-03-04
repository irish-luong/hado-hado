import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructField, _}
import util.ConfigUtil

/*
Object implement a spark job consume a Kafka topic
the makes an aggregation operation then return a dataframe
 */
object NetworkStreamingETL extends App {

  // Load configuration from Singleton object
  val env: String = "dev"
  val config = ConfigUtil.getConfig(env)

  // Start a Spark Session to a running spark cluster
  val spark: SparkSession = SparkSession
    .builder()
    .master(config.getString("spark.master"))
    .appName(getClass.getSimpleName)
    .getOrCreate()

  println("Start Spark Session ->>>> ")

  println("First SparkContext:")
  println("APP Name :" + spark.sparkContext.appName)
  println("Deploy Mode :" + spark.sparkContext.deployMode)
  println("Master :" + spark.sparkContext.master)

  // Import all implicit variables of Spark
  import org.apache.spark.sql.functions._
  import spark.implicits._

  val schema = StructType(
    List(
      StructField("time", LongType, false),
      StructField("networkSignal", ArrayType(StructType(Array(
              StructField("time", LongType, false),
              StructField("networkType", StringType, false),
              StructField("rxSpeed", DoubleType, false),
              StructField("txSpeed", DoubleType, false),
              StructField("rxData", LongType, false),
              StructField("txData", LongType, false),
              StructField("latitude", DoubleType, false),
              StructField("longitude", DoubleType, false)
      ))))
    )
  )

  print("PLAINTEXT://broker:29092")

  // Create streaming from Kafka to Dataframe
  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "PLAINTEXT://broker:29092")
    .option("subscribe", "hado.topic.network")
    .load()
    .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .select(from_json($"value", schema).alias("data"))
    .createOrReplaceTempView("network_signals")
//
  val sql =
    """
      |SELECT
      | WINDOW(FROM_UNIXTIME(x.signal.time/1000), "1 hour", "10 minutes") as evenWindow,
      | x.signal.networkType AS networkType,
      | AVG(x.signal.rxSpeed) AS avgRxSpeed,
      | AVG(x.signal.txSpeed) AS avgTxSpeed,
      | MIN(x.signal.rxSpeed) AS minRxSpeed,
      | MIN(x.signal.txSpeed) AS minTxSpeed,
      | MAX(x.signal.rxSpeed) AS maxRxSpeed,
      | MAX(x.signal.txSpeed) AS maxTxSpeed
      |FROM (
      | SELECT EXPLODE(data.networkSignal) as signals FROM network_signals
      |) x
      |GROUP BY
      | evenWindow,
      | networkType
      |ORDER BY
      | evenWindow,
      | networkType
      |""".stripMargin

  // Run SQL query
  val query = spark.sql(sql)

  val result = query.writeStream
    .format("console")
    .outputMode("complete")
    .option("truncate", false)
    .start()

  result.awaitTermination()
}