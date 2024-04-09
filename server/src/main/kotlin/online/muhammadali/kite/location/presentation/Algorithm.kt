package online.muhammadali.kite.location.presentation

import kotlinx.serialization.Serializable

@Serializable
enum class Algorithm{
    AES,
    RSA;
    fun getCipherMode(): String = when(this) {
        AES -> "$name/CBC/PKCS5Padding"
        RSA -> "$name/ECB/NoPadding"
    }
}