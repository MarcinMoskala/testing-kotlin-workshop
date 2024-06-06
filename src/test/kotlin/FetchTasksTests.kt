import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class FetchTasksTests {
    private val someTasks = listOf(Task("1", 123), Task("2", 456))
    private val someException = ApiException(500, "Some exception")

    @Test
    fun `should resume with result`(): Unit = runTest {
        // todo
    }

    @Test
    fun `should resume with exception`(): Unit = runTest {
        // todo
    }

    @Test
    fun `should support cancellation`(): Unit = runTest {
        // todo
    }

    class FakeFetchTasksCallbackUseCase: FetchTasksCallbackUseCase {
        var onSuccess: ((List<Task>) -> Unit)? = null
        var onError: ((Throwable) -> Unit)? = null
        var onCancelled: (()->Unit)? = null

        override fun fetchTasks(onSuccess: (List<Task>) -> Unit, onError: (Throwable) -> Unit): Cancellable {
            this.onSuccess = onSuccess
            this.onError = onError
            return Cancellable { onCancelled?.invoke() }
        }
    }
}
