package online.muhammadali.kite.auth.data.util.security

import online.muhammadali.kite.auth.domain.models.TokenConfig
import java.io.File
import kotlin.time.Duration.Companion.minutes
import kotlin.time.times

val JwtConfigurations = TokenConfig(
    issuer = "online.muhammadali.kite",
    audience = "audience", // todo
    expiresDuration = (30 * 24 * 60.minutes).inWholeMilliseconds,
    secret = getSecretKey()
)

fun getSecretKey(): ByteArray {
    //  todo use a secure way to store the secrete key
    val file = File("secret.key")

    val key: ByteArray
    if (!file.exists()) {
        key = Hmac512KeyGenerator().generateSecretKey()
        file.writeBytes(key)
    }
    else
        key = file.readBytes()

    return key
}
