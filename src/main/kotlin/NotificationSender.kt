@file:OptIn(ExperimentalCoroutinesApi::class)

import kotlinx.coroutines.*

class NotificationsSender(
    private val client: NotificationsClient,
    private val exceptionCollector: ExceptionCollector,
    dispatcher: CoroutineDispatcher,
) {
    private val handler = CoroutineExceptionHandler { _, throwable ->
        exceptionCollector.collectException(throwable)
    }
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + handler + dispatcher)

    fun sendNotifications(notifications: List<Notification>) {
        for (n in notifications) {
            scope.launch {
                client.send(n)
            }
        }
    }

    fun cancel() {
        scope.coroutineContext.job.cancelChildren()
    }
}

data class Notification(val id: String)

interface NotificationsClient {
    suspend fun send(notification: Notification)
}

interface ExceptionCollector {
    fun collectException(throwable: Throwable)
}