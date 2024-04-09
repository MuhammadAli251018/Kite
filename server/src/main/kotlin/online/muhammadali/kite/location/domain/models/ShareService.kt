package online.muhammadali.kite.location.domain.models


data class Subscriber(val id: String)

sealed interface SharedData {
    data class SharedLocation(val location: Location) : SharedData
}

abstract class ShareService {
    abstract val onDataShared: (SharedData, List<Subscriber>) -> Unit

    fun share(update: SharedData) {

    }
}