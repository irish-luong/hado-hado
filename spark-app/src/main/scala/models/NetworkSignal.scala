package models

import java.sql._
import scala.util.Random
import java.util.UUID
import org.json4s.native.Serialization
import org.json4s.native.Serialization.write
import org.json4s.Formats._
import org.json4s.DefaultFormats


class NetworkSignal {

    case class SignalGenerator(
        time: Long,
        networkType: String,
        rxSpeed: Double,
        txSpeed: Double,
        rxData: Long,
        txData: Long,
        latitude: Double,
        longitude: Double
    )

    val trainLines = List(
        "R1", "R2", "R3", "R4", "RT1", "L3",
        "S2", "L7", "RL2", "L5", "L1", "S1"
    )
    def randomLines: String = trainLines(Random.nextInt(trainLines.size))
    def randomPassengerCount: Int = Random.nextInt(100)
    def randomTrainNumber: Int = Random.nextInt(100)

    def randomDeviceType: String = {
        val ranNum = Random.nextInt(10)
        if (ranNum % 2 == 0) {
            return "wifi"
        }
        return "mobile"
    } 

    def genSignal: SignalGenerator = SignalGenerator(
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
    val random = new NetworkSignal()
    
    println(write(random.genSignal))
    // println(UUID.randomUUID().toString)
}
