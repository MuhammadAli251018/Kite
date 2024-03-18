package online.muhammadali.kite.common.domain

import io.ktor.server.auth.Principal
import online.muhammadali.kite.auth.domain.models.TokenClaim

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
