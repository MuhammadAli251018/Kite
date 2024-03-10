package online.muhammadali.kite.auth.domain.models

import io.ktor.server.auth.Principal


data class User(
    val id: String,
    val userInfo: UserInfo
) : Principal

data class UserInfo(
    val name: String,
    val email: String,
)

