package online.muhammadali.kite.location.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class Message {

    @Serializable
    data class SharedLocation(val location: Location, val userId: String) : Message()
    @Serializable
    data class ReceivedLocation(val location: Location, val senderId: String): Message()
    @Serializable
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


    }
}