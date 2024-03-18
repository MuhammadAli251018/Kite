package online.muhammadali.kite.auth.data.util.security

import online.muhammadali.kite.auth.domain.models.TokenConfig
import java.io.File
import kotlin.time.Duration.Companion.minutes
import kotlin.time.times



/*fun getSecretKey(): ByteArray {
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
}*/
