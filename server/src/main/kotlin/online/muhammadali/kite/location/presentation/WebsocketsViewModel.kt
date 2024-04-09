package online.muhammadali.kite.location.presentation

interface WebsocketsViewModel {

    suspend fun registerConnection(
        userId: String,
        publicKey: String,
        sendMessage: suspend (message: String) -> Unit
    ): ConnectionManager
}