package online.muhammadali.kite.location.security

import online.muhammadali.kite.location.domain.models.MessageCipher
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MessageCipherImp : MessageCipher {
    private val algorithm = "AES/CBC/PKCS5Padding"
    private val cipher = Cipher.getInstance(algorithm)

    override fun encryptMessage(message: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        return cipher.doFinal(message)
    }

    override fun decryptMessage(encryption: ByteArray, key: ByteArray): ByteArray {
        val secretKey = SecretKeySpec(key, "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        return cipher.doFinal(encryption)
    }
}