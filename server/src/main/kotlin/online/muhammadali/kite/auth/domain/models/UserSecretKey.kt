package online.muhammadali.kite.auth.domain.models

data class UserSecretKey(
    val key: ByteArray,
    val expireTime: Long
) {
    fun isExpired() = expireTime < System.currentTimeMillis()
}
