package online.muhammadali.kite.location.security

import javax.crypto.KeyGenerator

class SymmetricKeyGenerator {
    enum class Algorithm{
        AES
    }

    data class SymmetricKey(val key: ByteArray, val algorithm: Algorithm = Algorithm.AES)

    fun generateKey(algorithm: Algorithm, sizeInBytes: Int = 32): SymmetricKey {
        val keyGenerator = KeyGenerator.getInstance(algorithm.name)
        keyGenerator.init(sizeInBytes)
        return SymmetricKey(key = keyGenerator.generateKey().encoded, algorithm)
    }

}