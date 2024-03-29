package online.muhammadali.kite.auth.data.source

import com.mongodb.client.model.Filters.*
import com.mongodb.client.model.Updates.set
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.first
import online.muhammadali.kite.auth.data.enitities.UserEntity
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Success
import online.muhammadali.kite.common.utl.Result
import org.bson.types.ObjectId

class UsersDb(db: MongoDatabase) {
    private val collection = db.getCollection<UserEntity>("users")
    
    suspend fun getById(id: ObjectId): Result<UserEntity> {

        return try {
            val result = collection.find(eq(UserEntity::_id.name, id)).first()
            Success(result)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    suspend fun getByEmail(id: String): Result<UserEntity> {

        return try {
            val result = collection.find(eq(UserEntity::email.name, id)).first()
            Success(result)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    suspend fun deleteData(data: UserEntity): Result<Unit> {
        return try {
            val result = collection.deleteOne(filter = eq(UserEntity::_id.name, data._id))
            if (result.wasAcknowledged())
                Success(Unit)
            else
                throw IOException("Couldn't delete user")
        } catch (e: Exception) {
            Failure(e)
        }
    }

    suspend fun updateData(data: UserEntity): Result<Unit> {
        return try {
            val result = collection.updateOne(
                filter = eq(UserEntity::_id.name, data._id),
                listOf(set(data::name.name, data.name), set(data::email.name, data.email))
            )
            if (result.wasAcknowledged())
                Success(Unit)
            else
                throw IOException("Couldn't update user")
        } catch (e: Exception) {
            Failure(e)
        }
    }

    suspend fun writeData(data: UserEntity): Result<Unit> {
        return try {
            val result = collection.insertOne(data)
            if (result.wasAcknowledged())
                Success(Unit)
            else
                throw IOException("Couldn't add new user")
            } catch (e: Exception) {
                Failure(e)
            }

    }
}