package online.muhammadali.kite.location.presentation

import kotlinx.serialization.Serializable
import online.muhammadali.kite.location.domain.models.Location

@Serializable
sealed class Message {

    //  directed message
    @Serializable
    data class SharedLocation(val location: Location, val ownerId: String) : Message()
    /*@Serializable
    sealed class ErrorMessage : Message() {
        abstract val errorCode: Int
        abstract val errorMessage: String

        @Serializable
        data object MissingUserId : ErrorMessage() {
            override val errorCode: Int = 0
            override val errorMessage: String = "Missing user id"
        }
        @Serializable
        data object MissingPublicKey : ErrorMessage() {
            override val errorCode: Int = 0
            override val errorMessage: String = "Missing public key"
        }

        @Serializable
        data object InvalidPublicKey : ErrorMessage() {
            override val errorCode: Int = 0
            override val errorMessage: String = "Invalid public key"
        }
    }*/
}