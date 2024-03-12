package online.muhammadali.kite.auth.data.enitities

import online.muhammadali.kite.auth.domain.models.User
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class UserEntity(
    @BsonId
    val _id: ObjectId,
    val name: String,
    val email: String
)


fun User.toUserEntity() = UserEntity(
    _id = ObjectId(id),
    name = name,
    email = email
)

fun getNewUserEntity(name: String, email: String) = UserEntity(
    _id = ObjectId(),
    name = name,
    email = email
)

fun UserEntity.toUser() = User(
    id = _id.toString(),
    name = name,
    email = email
)