import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FetchTasksUseCase(
    private val callbackUseCase: FetchTasksCallbackUseCase
) {
    @Throws(ApiException::class)
    suspend fun fetchTasks(): List<Task> = suspendCancellableCoroutine { continuation: CancellableContinuation<List<Task>> ->
        val call = callbackUseCase.fetchTasks(
            onSuccess = { continuation.resume(it) },
            onError = { continuation.resumeWithException(it) }
        )
        continuation.invokeOnCancellation { call.cancel() }
    }
}

interface FetchTasksCallbackUseCase {
    fun fetchTasks(onSuccess: (List<Task>) -> Unit, onError: (Throwable) -> Unit): Cancellable
}

fun interface Cancellable {
    fun cancel()
}
data class Task(val name: String, val priority: Int)
class ApiException(val code: Int, message: String): Throwable(message)