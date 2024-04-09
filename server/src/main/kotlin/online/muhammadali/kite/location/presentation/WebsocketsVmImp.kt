package online.muhammadali.kite.location.presentation

class WebsocketsVmImp (
    private val channel: MessageChannel
) : WebsocketsViewModel {

    override suspend fun registerConnection(
        userId: String,
        publicKey: String,
        sendMessage: suspend (message: String) -> Unit
    ): ConnectionManager {
        return ConnectionManagerImp(
            channel = channel,
            clientPublicKey = publicKey,
            sendMessage = sendMessage
        )
    }
}