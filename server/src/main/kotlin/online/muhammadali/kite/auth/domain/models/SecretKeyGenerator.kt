package online.muhammadali.kite.auth.domain.models

import com.auth0.jwt.algorithms.Algorithm
import java.math.BigInteger
import java.security.SecureRandom
import java.security.interfaces.RSAKey
import kotlin.random.Random

interface SecretKeyGenerator {
    fun generateSecretKey(): ByteArray
}

