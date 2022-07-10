fun main() {
    val hp: BatteryTechnology = Hp()
    hp.powerOff()
    hp.powerOn()
    hp.checkBatteryStatus()

    val iron: ElectronicTechnology = Iron()
    iron.powerOn()
    iron.powerOff()
}
//opposite to principle
/*
data class Crash(val exception: String?)

interface ElectronicTechnology {
    fun powerOn(): Crash
    fun checkBatteryStatus(): Crash
    fun powerOff(): Crash
}

class Hp : ElectronicTechnology {
    override fun powerOn(): Crash = Crash("Low battery")

    override fun checkBatteryStatus(): Crash = Crash("Low")

    override fun powerOff(): Crash = Crash(null)
}

class Iron : ElectronicTechnology {
    override fun powerOn(): Crash = Crash(null)

    override fun checkBatteryStatus(): Crash {
        throw java.lang.UnsupportedOperationException("what,battery status?")
    }

    override fun powerOff(): Crash = Crash("All fine, bro")
}
*/

//for to principle
data class Crash(val exception: String?)

interface ElectronicTechnology {
    fun powerOn(): Crash
    fun powerOff(): Crash
}

interface BatteryTechnology : ElectronicTechnology {
    fun checkBatteryStatus(): Crash
}

class Hp : BatteryTechnology {
    override fun powerOn(): Crash = Crash("Low battery")

    override fun checkBatteryStatus(): Crash = Crash("Low")

    override fun powerOff(): Crash = Crash(null)
}

class Iron : ElectronicTechnology {
    override fun powerOn(): Crash = Crash(null)

    override fun powerOff(): Crash = Crash("All fine, bro")
}
