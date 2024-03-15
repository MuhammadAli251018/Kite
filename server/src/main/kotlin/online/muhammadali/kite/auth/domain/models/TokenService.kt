package online.muhammadali.kite.auth.domain.models

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.Principal
import online.muhammadali.kite.auth.data.util.security.JwtConfigurations


interface TokenService <T> {
    fun generateToken(config: TokenConfig = JwtConfigurations, data: T): String
}