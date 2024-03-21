package online.muhammadali.kite.location.domain.models

interface MessageCipher {
    fun encryptMessage(message: ByteArray, key: ByteArray): ByteArray
    //fun encryptMessage(message: String, key: ByteArray): String
    fun decryptMessage(encryption: ByteArray, key: ByteArray): ByteArray
}

fun MessageCipher.encryptMessage(message: String, key: ByteArray) =
    encryptMessage(message= message.encodeToByteArray(), key = key).decodeToString()

fun MessageCipher.decryptMessage(encryption: String, key: ByteArray) =
    decryptMessage(encryption= encryption.encodeToByteArray(), key = key).decodeToString()
