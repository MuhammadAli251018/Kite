package online.muhammadali.kite.auth.data.util.security

import online.muhammadali.kite.auth.domain.models.SecretKeyGenerator
import java.security.SecureRandom

class Hmac512KeyGenerator : SecretKeyGenerator  {
    override fun generateSecretKey(): ByteArray {
        val key = ByteArray(128)
        SecureRandom().nextBytes(key)
        return key
    }
}