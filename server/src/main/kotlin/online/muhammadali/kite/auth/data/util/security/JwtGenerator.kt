package online.muhammadali.kite.auth.data.util.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import online.muhammadali.kite.auth.domain.models.TokenConfig
import online.muhammadali.kite.auth.domain.models.TokenService
import online.muhammadali.kite.auth.domain.models.User
import online.muhammadali.kite.auth.domain.models.addClaims

class JwtGenerator : TokenService<User>{
    override fun generateToken(config: TokenConfig, data: User): String {
        return JWT.create()
            .withIssuer(config.issuer)
            .addClaims(data.getClaims())
            .sign(Algorithm.HMAC512(config.secret))
    }
}