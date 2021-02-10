package services

import java.util.Properties
import org.apache.kafka.clients.producer._


class Producer {

    def publish(
        topic: String, 
        key: String, 
        value: Any
        ): Unit = {

        // Set configuration for Kafka producer
        var props = new Properties()
        props.put("bootstrap.servers", "localhost:9092")
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

        // Initate a Kafka producer instance
        val producer = new KafkaProducer[String, String](props)

        // Initate records as messages to publish to kafka
        val record = new ProducerRecord[String, String](topic, "key", "value")

        try{
            producer.send(record)
        } catch {
            case _: Throwable => println("Got some other kind of Throwable exception")
        } finally {
            producer.close()
        }

    }

}