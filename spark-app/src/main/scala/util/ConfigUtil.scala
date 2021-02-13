package util

import com.typesafe.config._

object ConfigUtil {

    def getConfig(configName: String = "dev"): Config = {
        val configFile = configName + ".conf"
        try {
            val config = ConfigFactory.load(configFile)
            config
        } catch {
            case e: Throwable => throw e
        }

    }
}