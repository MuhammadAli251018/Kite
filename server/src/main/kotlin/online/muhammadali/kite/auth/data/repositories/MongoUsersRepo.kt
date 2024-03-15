package online.muhammadali.kite.auth.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import online.muhammadali.kite.auth.data.enitities.toUser
import online.muhammadali.kite.auth.data.enitities.toUserEntity
import online.muhammadali.kite.auth.data.source.UsersDb
import online.muhammadali.kite.common.domain.User
import online.muhammadali.kite.auth.domain.repositories.UsersDBRepo
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Result
import online.muhammadali.kite.common.utl.Success
import org.bson.types.ObjectId

class MongoUsersRepo(private val usersDb: UsersDb) : UsersDBRepo {

    override suspend fun addNewUser(user: User): Flow<Result<Unit>> = flow { emit(usersDb.writeData(user.toUserEntity()))}
    override suspend fun updateUser(user: User): Flow<Result<Unit>> = flow { emit(usersDb.updateData(user.toUserEntity()))}
    override suspend fun deleteUser(user: User): Flow<Result<Unit>> = flow { emit(usersDb.deleteData(user.toUserEntity()))}
    override suspend fun getUser(id: String): Flow<Result<User>> {
        return flow {
            usersDb.getById(ObjectId(id)).apply {
                emit(
                    when (this) {
                        is Success -> Success(data.toUser())
                        is Failure -> Failure(throwable)
                    }
                )
            }
        }
    }

    override suspend fun getUserByEmail(email: String): Flow<Result<User>> {
        return flow {
            usersDb.getByEmail(email).apply {
                emit(
                    when (this) {
                        is Success -> Success(data.toUser())
                        is Failure -> Failure(throwable)
                    }
                )
            }
        }
    }
}