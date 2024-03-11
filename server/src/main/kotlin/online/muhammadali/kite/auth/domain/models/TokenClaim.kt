package online.muhammadali.kite.auth.domain.models

import com.auth0.jwt.JWTCreator

data class TokenClaim(
    val name: String,
    val value: String
)


fun JWTCreator.Builder.addClaims(claims: List<TokenClaim>):JWTCreator.Builder  {
    claims.forEach {
        withClaim(it.name, it.value)
    }

    return this
}