package online.muhammadali.kite

import Greeting
import SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import online.muhammadali.kite.auth.presentation.routes.token.setupRequestTokenRoute
import online.muhammadali.kite.plugins.configureAuthentication
import online.muhammadali.kite.plugins.configureRouting
import online.muhammadali.kite.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureAuthentication()
    configureSerialization()
    configureRouting()
}