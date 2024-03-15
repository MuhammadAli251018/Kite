package online.muhammadali.kite.auth.presentation.routes.token

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.ContentTransformationException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import online.muhammadali.kite.common.utl.getOrThrow

@Serializable
data class RequestTokenData(
    val userName: String,
    val token: String
)

fun Route.setupRequestTokenRoute(viewModel: TokenRequestViewModel) {
    route("/auth"){
        get("/request-token") {
            try {

                val requestData = call.receive<RequestTokenData>()
                val result = viewModel.verifyToken(requestData.token).getOrThrow()
                val email = result.userEmail

                withContext(Dispatchers.IO){

                    viewModel.getUserId(email, requestData.userName).collectLatest {
                        val id = it.getOrThrow()
                        val token = viewModel.generateToken(id= id, email= email, name= requestData.userName)
                        call.respond(message = token, status = HttpStatusCode.OK)
                    }
                }
            } catch (e: Exception) {
                call.respond(when(e) {
                    is ContentTransformationException -> HttpStatusCode.BadGateway
                    is AuthException -> HttpStatusCode.BadGateway
                    else -> HttpStatusCode.InternalServerError
                })
            }
        }
    }
}


class AuthException(override val message: String) : Exception()

inline fun getVerifier(block: ClientTokenVerifier.() -> Unit) = GoogleTokenVerifier().apply {
    block()
}