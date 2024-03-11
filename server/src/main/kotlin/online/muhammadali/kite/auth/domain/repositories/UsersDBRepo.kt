package online.muhammadali.kite.auth.domain.repositories

import online.muhammadali.kite.auth.domain.models.User
import online.muhammadali.kite.common.utl.Result

interface UsersDBRepo {

    suspend fun addNewUser(user: User): Result<Unit>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun deleteUser(user: User): Result<Unit>
    suspend fun getUser(id: String): Result<User>

}