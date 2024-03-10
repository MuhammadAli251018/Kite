package online.muhammadali.kite.auth.domain.repositories

import online.muhammadali.kite.auth.domain.models.User

interface UsersDBRepo {

    suspend fun addNewUser(user: User): Result<Unit>
    suspend fun updateUser(user: User): Result<Unit>
    suspend fun deleteUser(user: User): Result<Unit>
    suspend fun getUser(id: String): Result<User>

}