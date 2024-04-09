package online.muhammadali.kite.location.presentation

data class Subscriber(val id: Int, val onMessage: suspend (Message) -> Unit)

fun interface SubscriberScope {
    fun getId(): Int
}
interface MessageChannel {
    suspend fun broadcastMessage(message: Message)
    suspend fun subscribe(subscriber: SubscriberScope.() -> Subscriber)
    suspend fun unsubscribe(id: Int)
}
