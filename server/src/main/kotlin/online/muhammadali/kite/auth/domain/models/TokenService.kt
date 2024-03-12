package online.muhammadali.kite.auth.domain.models

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.Principal


interface TokenService <T> {
    fun generateToken(config: TokenConfig, data: T): String
}