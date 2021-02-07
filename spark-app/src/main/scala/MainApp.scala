package Main

import services.Producer

object Main extends App {

    override def main(args: Array[String]): Unit = {
        val kafkaProducer = new Producer()
        kafkaProducer.run()
        println("Hello, world xx")
    }
}


