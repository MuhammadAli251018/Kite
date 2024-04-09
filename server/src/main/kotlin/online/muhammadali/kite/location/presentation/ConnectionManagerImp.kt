package online.muhammadali.kite.location.presentation

import kotlinx.coroutines.runBlocking

class ConnectionManagerImp (
    clientPublicKey: String,
    private val channel: MessageChannel,
    override val sendMessage: suspend (message: String) -> Unit,
) : ConnectionManager() {

    private var connectionStateHandler: ConnectionStateHandler

    init {
        runBlocking {
            connectionStateHandler = ConnectionStateHandler.InitialStateHandler.getInitialState(
                clientPublicKey = clientPublicKey,
                onStateChange = {
                    this@ConnectionManagerImp.connectionStateHandler = it
                },
                sendMessage = sendMessage,
                channel = channel
            )
        }
    }

    override suspend fun onMessageReceived(message: String) {
        connectionStateHandler.onMessageReceived(message)
    }

}
