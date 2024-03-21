package online.muhammadali.kite.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import online.muhammadali.kite.common.domain.User

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt {
            //val realm = "kite"
            val issuer = "online.muhammadali.kite"

            verifier(
                //  Todo: Complete
                JWT
                    .require(Algorithm.HMAC256("test secret"))
                    .withIssuer(issuer)
                    .build()
            )

            validate {credentials ->
                val id = credentials.payload.getClaim("id").asString()
                val name = credentials.payload.getClaim("name").asString()
                val email = credentials.payload.getClaim("email").asString()
                val expireTime = credentials.payload.getClaim("exp").asLong()

                if (id != null && name != null && email != null && expireTime != null) {
                    if (expireTime < System.currentTimeMillis()) {
                        respond("expired token")
                        null
                    }
                    else
                        User(id = id, name = name, email = email)
                }
                else // todo create response for the API {
                {
                    respond("unauthorized")
                    null

                }

            }
        }
    }
}

