package models

import java.sql._
import java.util.UUID
import scala.util.Random
import org.json4s.DefaultFormats
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write


case class NetworkData(
    time: Long,
    deviceUUID: String,
    networkSignals: List[NetworkSignal]
) 

case class NetworkSignal(
        time: Long,
        networkType: String,
        rxSpeed: Double,
        txSpeed: Double,
        rxData: Long,
        txData: Long,
        latitude: Double,
        longitude: Double
    )

class SignalGenerator {

    def randomDeviceType: String = {
        val ranNum = Random.nextInt(10)
        if (ranNum % 2 == 0) {
            return "wifi"
        }
        return "mobile"
    } 

    def genSignal: NetworkSignal = NetworkSignal(
        System.currentTimeMillis,
        randomDeviceType,
        Random.nextInt(1000),
        Random.nextInt(1000),
        Random.nextInt(1000),
        Random.nextInt(1000),
        Random.nextInt(10) * 10 + 1,
        Random.nextInt(10) * 10 + 1
    )
}


object RandomRun extends App {
    
    implicit val formats = DefaultFormats
    val random = new SignalGenerator()
    println(write(random.genSignal))
}
