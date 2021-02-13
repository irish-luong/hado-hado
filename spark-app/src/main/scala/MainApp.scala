import java.util.{UUID, Properties}
import scala.util._
import common.AppConstant
import models.{NetworkData, NetworkSignal, SignalGenerator}
import util.ConfigUtil


import org.json4s.DefaultFormats
import org.json4s.native.Serialization.write
import org.apache.kafka.clients.producer._


object ProducerWorker extends App {


    override def main(args: Array[String]): Unit = {

        var props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        // Initate a Kafka producer instance
        val producer = new KafkaProducer[String, String](props)

        implicit val formats = DefaultFormats

        try {
            for ( i <- 0 to AppConstant.produce_run ) {
                
                var uuid = UUID.randomUUID().toString
                var systemTime = System.currentTimeMillis

                // Declare a empty list contains network signals
                var signals: List[NetworkSignal] = List()

                for ( j <- 0 to Random.nextInt(5) ) {
                    var generator = new SignalGenerator()
                    var networkSignal: NetworkSignal = generator.genSignal
                    signals = networkSignal :: signals
                }
            
                var networkData = NetworkData(
                    systemTime,
                    signals
                )
                
                val message = write(networkData)

                val record = new ProducerRecord[String, String](
                    "hado.topic.network", 
                    uuid, 
                    message
                    )

                producer.send(record)
                
                println("Publish message topic: " + uuid + " ->> value: " + message)

                Thread.sleep(30000)
            }
        } catch {
             case _: Throwable => println("Got some other kind of Throwable exception")
        } finally {
            producer.close()
        }

    }
}


object Console extends App {

    val config = ConfigUtil.getConfig(
        scala.util.Properties.envOrElse("mode", "uat")
    )
    println(config.getString("kafka.bootstrap.servers"))

}
