package online.muhammadali.kite.location.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WsChannel : MessageChannel {
    private val channel = MutableSharedFlow<Message>()
    private val subscribers = mutableListOf<Subscriber>()
    private val collectingScope = CoroutineScope(Dispatchers.Main)

    init {
        collectingScope.launch {
            channel.collectLatest {message ->
                subscribers.forEach { subscriber ->
                    launch {
                        subscriber.onMessage(message)
                    }
                }
            }
        }
    }

    override suspend fun broadcastMessage(message: Message) {
        channel.emit(message)
    }

    override suspend fun subscribe(subscriber: SubscriberScope.() -> Subscriber) {
        SubscriberScope {
            subscribers.lastIndex + 1
        }.apply {
            val newSubscriber = subscriber()
            subscribers.add(newSubscriber)
        }
    }

    override suspend fun unsubscribe(id: String) {
        subscribers.removeIf {
            it.id == id
        }
    }

}