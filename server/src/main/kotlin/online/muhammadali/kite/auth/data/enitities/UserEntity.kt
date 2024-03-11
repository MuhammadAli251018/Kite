package online.muhammadali.kite.auth.data.enitities

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserEntity(
    @BsonId
    val userId: ObjectId,
    val name: String,
    val email: String
)
