package Main

import java.util.UUID
import scala.util._
import services.Producer
import common.AppConstant
import models.{NetworkData, NetworkSignal, SignalGenerator}


import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write


object Main extends App {

    override def main(args: Array[String]): Unit = {
        // val kafkaProducer = new Producer()
        // kafkaProducer.run()
        // println("Hello, world xx")

        implicit val formats = DefaultFormats

        for ( i <- 0 to AppConstant.produce_run ) {
            
            var uuid = UUID.randomUUID().toString()
            var systemTime = System.currentTimeMillis

            // Declare a empty list contains network signals
            var signals: List[NetworkSignal] = List()

            for ( j <- 0 to Random.nextInt(5) ) {
                var generator = new SignalGenerator()
                var networkSignal: NetworkSignal = generator.genSignal

                signals = networkSignal :: signals
            }
            
            var networkData = new NetworkData(
                systemTime,
                uuid,
                signals
            )

            println(write(networkData))
        }
        
    }
}


