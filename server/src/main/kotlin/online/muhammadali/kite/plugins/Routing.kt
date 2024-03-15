package online.muhammadali.kite.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import online.muhammadali.kite.auth.presentation.routes.token.setupRequestTokenRoute
import online.muhammadali.kite.common.di.PresentationModule.tokenRequestViewModel

fun Application.configureRouting() {
    routing {
        /*
        authenticate {
            get("/") {
                call.respond(message = "Hello World!", status = HttpStatusCode.OK)
            }
        }*/

        setupRequestTokenRoute(tokenRequestViewModel)
    }
}