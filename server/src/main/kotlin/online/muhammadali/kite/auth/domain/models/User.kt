package online.muhammadali.kite.auth.domain.models

import io.ktor.server.auth.Principal

data class User(
    val id: String,
    val name: String,
    val email: String,
) : Principal {
    fun getClaims(): List<TokenClaim> = listOf(
        TokenClaim("id", id),
        TokenClaim("name", name),
        TokenClaim("email", email)
    )
}
