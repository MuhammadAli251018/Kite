package online.muhammadali.kite.auth.data.source

import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import online.muhammadali.kite.common.utl.Result


interface MongoDb <T> {

    suspend fun getById(id: ObjectId): Result<T>
    suspend fun writeData(data: T): Result<Unit>
    suspend fun updateData(data: T): Result<Unit>
    suspend fun deleteData(data: T): Result<Unit>
}