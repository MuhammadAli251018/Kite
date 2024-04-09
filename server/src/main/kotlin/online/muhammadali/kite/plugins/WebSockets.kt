package online.muhammadali.kite.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.*
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import online.muhammadali.kite.common.domain.User
import online.muhammadali.kite.location.presentation.Message
import online.muhammadali.kite.location.presentation.WebsocketsViewModel
import java.time.Duration

fun Application.configureWebSockets(viewModel: WebsocketsViewModel) {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        authenticate {
            webSocket("/webSocket/{public_key}") {

                val id = (call.principal<User>() ?: throw Exception("Can't get id")).id
                val key = call.parameters["public_key"] ?: throw Exception("Can't get the key")

                launch {
                    val connectionManager = viewModel.registerConnection(
                        userId = id,
                        publicKey = key,
                    ) {
                        send(Frame.Text(it))
                    }

                    for (frame in incoming) {
                        frame as? Frame.Text ?: continue
                        val receivedText = frame.readText()
                        connectionManager.onMessageReceived(receivedText)
                    }
                }

            }
        }
    }
}