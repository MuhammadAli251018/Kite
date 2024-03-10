package online.muhammadali.kite.auth.domain.models

import java.security.Principal

interface TokenService <T: Principal> {
    fun generateToken(config: TokenConfig, data: T): String
}