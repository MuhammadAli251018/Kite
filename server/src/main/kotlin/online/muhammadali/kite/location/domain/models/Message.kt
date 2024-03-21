package online.muhammadali.kite.location.domain.models

//  Todo: Generate public & private key to communicate with client safely
sealed class Message {

    data class SharedLocation(val location: Location, val userId: String) : Message()

    data class ReceivedLocation(val location: Location, val senderId: String): Message()
}