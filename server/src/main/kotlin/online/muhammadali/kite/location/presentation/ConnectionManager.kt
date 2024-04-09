package online.muhammadali.kite.location.presentation

abstract class ConnectionManager {
    protected abstract val sendMessage: suspend (message: String) -> Unit

    abstract suspend fun onMessageReceived(message: String)

}
