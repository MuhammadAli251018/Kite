package online.muhammadali.kite.plugins

import io.ktor.http.auth.parseAuthorizationHeader
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
import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.presentation.routes.token.AuthException
import online.muhammadali.kite.common.domain.User
import java.time.Duration

fun Application.configureWebSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        authenticate {
            webSocket("/webSocket") {
                val id = (call.principal<User>() ?: throw Exception("Can't get id")).id
                //  todo get the key

                send(Frame.Text("sender: Connected "))
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    println("received: $receivedText")
                    send(Frame.Text("You said: $receivedText"))
                }
            }
        }
    }
}