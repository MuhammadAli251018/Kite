package online.muhammadali.kite.auth.domain.repositories

import kotlinx.coroutines.flow.Flow
import online.muhammadali.kite.auth.domain.models.User
import online.muhammadali.kite.common.utl.Result

interface UsersDBRepo {

    suspend fun addNewUser(user: User): Flow<Result<Unit>>
    suspend fun updateUser(user: User): Flow<Result<Unit>>
    suspend fun deleteUser(user: User): Flow<Result<Unit>>
    suspend fun getUser(id: String): Flow<Result<User>>

}