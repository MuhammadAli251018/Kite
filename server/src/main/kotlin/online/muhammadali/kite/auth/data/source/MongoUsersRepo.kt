package online.muhammadali.kite.auth.data.source

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import online.muhammadali.kite.auth.data.enitities.UserEntity
import online.muhammadali.kite.auth.domain.models.User
import online.muhammadali.kite.auth.domain.repositories.UsersDBRepo
import online.muhammadali.kite.common.utl.Result

class MongoUsersRepo(private val dbCollection: MongoCollection<UserEntity>) : UsersDBRepo {

    override suspend fun addNewUser(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): Result<User> {
        TODO("Not yet implemented")
    }
}