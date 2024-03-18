package online.muhammadali.kite.plugins

import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import online.muhammadali.kite.auth.presentation.routes.token.TokenRequestViewModel
import online.muhammadali.kite.auth.presentation.routes.token.setupRequestTokenRoute
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        /*
        authenticate {
            get("/") {
                call.respond(message = "Hello World!", status = HttpStatusCode.OK)
            }
        }*/
        /*val viewModel: TokenRequestViewModel by inject()
        setupRequestTokenRoute(viewModel)*/
    }
}