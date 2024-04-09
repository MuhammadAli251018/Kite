package online.muhammadali.kite.location.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import online.muhammadali.kite.location.domain.models.Location
import online.muhammadali.kite.location.security.MessageCipherImp
import online.muhammadali.kite.location.security.SymmetricKeyGenerator
import java.util.Base64


sealed class ConnectionStateHandler {
    protected sealed interface CommunicationMessage
    protected interface MessageInterpreter <M: CommunicationMessage>{
        fun getMessageFromString(message: String): M
        fun messageToString(message: M): String
    }

    abstract val onStateChange: (newState: ConnectionStateHandler) -> Unit
    abstract val sendMessage: suspend (String) -> Unit
    abstract val channel: MessageChannel
    abstract suspend fun onMessageReceived(message: String)

    class InitialStateHandler private constructor(
        override val onStateChange: (newState: ConnectionStateHandler) -> Unit,
        override val sendMessage: suspend (String) -> Unit,
        override val channel: MessageChannel,
    ) : ConnectionStateHandler() {

        companion object {
            suspend fun getInitialState(
                clientPublicKey: String,
                onStateChange: (newState: ConnectionStateHandler) -> Unit,
                //  Todo add id to message
                sendMessage: suspend (String) -> Unit,
                channel: MessageChannel,
            ): InitialStateHandler {
                return InitialStateHandler(
                    onStateChange = onStateChange,
                    sendMessage = sendMessage,
                    channel = channel
                ).apply {
                    startCommunication(clientPublicKey)
                }
            }
        }

        private sealed interface InitialStateMessages : CommunicationMessage {
            data class ServerCommunicationKey(val key: String) : InitialStateMessages
            data object ClientReceivedKey : InitialStateMessages
            data object ClientInvalidKey : InitialStateMessages
            data object UnknownCommunicationMessage : InitialStateMessages
        }
        private class InitialStateInterpreter (
            private val key: SymmetricKeyGenerator.SymmetricKey
        ) : MessageInterpreter<InitialStateMessages> {

            private val cipher = MessageCipherImp(key.algorithm)
            override fun getMessageFromString(message: String): InitialStateMessages {
                return try {
                    val decryptedMessage = cipher.decryptMessage(
                        encryption = Base64.getDecoder().decode(message),
                        key.key
                    ).decodeToString()

                    Json.decodeFromString<InitialStateMessages>(decryptedMessage)

                } catch (e: Exception) {
                    InitialStateMessages.UnknownCommunicationMessage
                }
            }
            override fun messageToString(message: InitialStateMessages): String {
                return try {
                    val messageStr = Json.encodeToString(message)
                    cipher.encryptMessage(
                        message = messageStr.encodeToByteArray(),
                        key.key
                    ).decodeToString()
                }
                catch (e: Exception) {
                    throw Exception("Couldn't encode")
                }
            }
        }

        private val key: SymmetricKeyGenerator.SymmetricKey = SymmetricKeyGenerator().generateKey()
        private val interpreter = InitialStateInterpreter(key)

        private suspend fun startCommunication(clientPublicKey: String) {
            val encryptedKey = MessageCipherImp(Algorithm.RSA)
                .encryptMessage(
                    message = Json.encodeToString(key).encodeToByteArray(),
                    Base64.getDecoder().decode(clientPublicKey)
                )

            sendMessage(
                Json.encodeToString(
                    InitialStateMessages
                        .ServerCommunicationKey(
                            encryptedKey.decodeToString()
                        )
                )
            )
        }

        override suspend fun onMessageReceived(message: String) {
            when(interpreter.getMessageFromString(message)) {
                InitialStateMessages.ClientInvalidKey -> sendMessage("Todo")
                InitialStateMessages.ClientReceivedKey -> {
                    onStateChange(
                        CommunicationStateHandler(
                            key = key,
                            onStateChange = onStateChange,
                            sendMessage = sendMessage,
                            channel = channel
                        )
                    )
                }
                else -> sendMessage("Todo") // unknown message
            }
        }

    }



    class CommunicationStateHandler(
        key: SymmetricKeyGenerator.SymmetricKey,
        override val onStateChange: (newState: ConnectionStateHandler) -> Unit,
        override val sendMessage: suspend (String) -> Unit, override val channel: MessageChannel
    ) : ConnectionStateHandler() {

        private sealed interface CommunicationStateMessages : CommunicationMessage {
            abstract val id: String
            data class ClientShareLocation(val location: Location, override val id: String) : CommunicationStateMessages
            data class ServerPushLocation(val location: Location, override val id: String) : CommunicationStateMessages
            data object UnknownCommunicationMessage : CommunicationStateMessages {
                override val id: String
                    get() = TODO("Not yet implemented")
            }
        }

        private fun Message.toCommunicationMessage(): CommunicationStateMessages {
            return when(this) {
                is Message.SharedLocation -> CommunicationStateMessages.ServerPushLocation(id = ownerId, location = location)
            }
        }

        private class CommunicationStateInterpreter (
            private val key: SymmetricKeyGenerator.SymmetricKey
        ) : MessageInterpreter<CommunicationStateMessages> {

            private val cipher = MessageCipherImp(key.algorithm)
            override fun getMessageFromString(message: String): CommunicationStateMessages {
                return try {
                    val decryptedMessage = cipher.decryptMessage(
                        encryption = Base64.getDecoder().decode(message),
                        key.key
                    ).decodeToString()

                    Json.decodeFromString<CommunicationStateMessages>(decryptedMessage)

                } catch (e: Exception) {
                    CommunicationStateMessages.UnknownCommunicationMessage
                }
            }
            override fun messageToString(message: CommunicationStateMessages): String {
                return try {
                    val messageStr = Json.encodeToString(message)
                    cipher.encryptMessage(
                        message = messageStr.encodeToByteArray(),
                        key.key
                    ).decodeToString()
                }
                catch (e: Exception) {
                    throw Exception("Couldn't encode")
                }
            }
        }

        init {
            CoroutineScope(Dispatchers.Main).launch {
                channel.subscribe {
                    Subscriber(getId()) {
                        sendMessage(interpreter.messageToString(it.toCommunicationMessage()))
                    }
                }
            }
        }

        private val interpreter = CommunicationStateInterpreter(key)

        override suspend fun onMessageReceived(message: String) {
            when(interpreter.getMessageFromString(message)) {
                is CommunicationStateMessages.ClientShareLocation -> {}
                else -> sendMessage("Todo") // unknown message
            }
        }

    }
}