@file:OptIn(ExperimentalCoroutinesApi::class)

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NotificationsSenderTest {

    @Test
    fun `should send 20 notifications concurrently`() {
        // TODO
    }

    @Test
    fun `should not cancel other notifications, when one has exception`() {
        // TODO
    }

    @Test
    fun `should collect notification failures`() {
        // TODO
    }

    @Test
    fun `should cancel active coroutines`() {
        // Extra
    }

    @Test
    fun `should send new notifications after cancellation`() {
        // Extra
    }
}

class FakeNotificationsClient(
    val delayTime: Long = 0L,
    val failEvery: Int = Int.MAX_VALUE
) : NotificationsClient {
    var sent = emptyList<Notification>()
    var counter = 0
    var usedThreads = emptyList<String>()

    override suspend fun send(notification: Notification) {
        if (delayTime > 0) delay(delayTime)
        usedThreads += Thread.currentThread().name
        counter++
        if (counter % failEvery == 0) {
            throw FakeFailure(notification)
        }
        sent += notification
    }
}

class FakeFailure(val notification: Notification) : Throwable("Planned fail for notification ${notification.id}")

class FakeExceptionCollector : ExceptionCollector {
    var collected = emptyList<Throwable>()

    override fun collectException(throwable: Throwable) = synchronized(this) {
        collected += throwable
    }
}