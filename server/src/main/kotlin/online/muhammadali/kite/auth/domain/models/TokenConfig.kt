package online.muhammadali.kite.auth.domain.models

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expiresDuration: Long,
    val secret: ByteArray
)
