package online.muhammadali.kite.location.security

import online.muhammadali.kite.location.domain.models.MessageCipher
import online.muhammadali.kite.location.presentation.Algorithm
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MessageCipherImp(private val algorithm: Algorithm) : MessageCipher {
    private val cipher = Cipher.getInstance(algorithm.getCipherMode())

    /**@param message &
     * @param key should be UTF-8 encoded*/
    override fun encryptMessage(message: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, algorithm.name)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher.doFinal(message)
    }

    override fun decryptMessage(encryption: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, algorithm.name)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        return cipher.doFinal(encryption)
    }
}