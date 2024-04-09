package online.muhammadali.kite.location.security

import kotlinx.serialization.Serializable
import online.muhammadali.kite.location.presentation.Algorithm
import javax.crypto.KeyGenerator

class SymmetricKeyGenerator {
    @Serializable
    data class SymmetricKey(val key: ByteArray, val algorithm: Algorithm = Algorithm.AES)

    fun generateKey(algorithm: Algorithm = Algorithm.AES, sizeInBytes: Int = 32): SymmetricKey {
        val keyGenerator = KeyGenerator.getInstance(algorithm.name)
        keyGenerator.init(sizeInBytes)
        return SymmetricKey(key = keyGenerator.generateKey().encoded, algorithm)
    }

}