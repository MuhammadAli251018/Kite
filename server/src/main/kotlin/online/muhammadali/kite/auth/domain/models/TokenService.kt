package online.muhammadali.kite.auth.domain.models

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.Principal


interface TokenService <T: Principal> {
    fun generateToken(config: TokenConfig, data: T): String
}


/*
object JwtGenerator : TokenService<UserInfo> {
    override fun generateToken(config: TokenConfig, data: UserInfo): String {

            return JWT.create()
            .withIssuer(config.issuer)
            .addClaims(data.getClaims())
            .sign(Algorithm.HMAC256())


    }

}*/
