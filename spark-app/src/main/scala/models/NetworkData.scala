package models

import org.json4s.DefaultFormats
import org.json4s.Serialization.write

class NetworkData {

    private var time Long
    private var deviceUUID String
    private var networkSignal NetworkSignal

    def setDeviceUUID(deviceUUID: Int): NetworkData = {
        this.deviceUUID = deviceUUID
        return this
    }

    def getDeviceUUID(): String = {
        return this.deviceUUID
    }

    def setNetworkSignal(signal: NetworkSignal = null): NetworkData {
        if (! signal) {
            var networkSignal = new NetworkSignal()
            this.networkSignal = networkSignal.genSignal
        } else {
            this.networkSignal = signal
        }
        return this
    }

    def getNetworkSignal(): NetworkData = {

        if (this.networkSignal) {
            implicit val formats = DefaultFormats
            println(write(this.networkSignal))
        } else {
            println("Network signal is null ......")
        }
        return this.networkSignal
    }

    def setTime(): NetworkData = {
        this.time = System.currentTimeMillis
        return this
    }

    def getTime(): Long = {
        return this.time
    }

}