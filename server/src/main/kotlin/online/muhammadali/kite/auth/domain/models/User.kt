package online.muhammadali.kite.auth.domain.models

data class User(
    val userInfo: UserInfo,
    val secretKey: ByteArray
)
