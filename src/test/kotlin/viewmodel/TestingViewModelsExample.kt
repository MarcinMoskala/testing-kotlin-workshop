package viewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*
import kotlin.test.assertEquals

private val date1 = Date
    .from(Instant.now().minusSeconds(10))
private val date2 = Date
    .from(Instant.now().minusSeconds(20))
private val date3 = Date
    .from(Instant.now().minusSeconds(30))

private val aName = "Some name"
private val someNews =
    listOf(News(date3), News(date1), News(date2))
private val viewModel = MainViewModel(
    userRepo = FakeUserRepository(aName),
    newsRepo = FakeNewsRepository(someNews)
)

class FakeUserRepository(val name: String) : UserRepository {
    override suspend fun getUser(): UserData {
        delay(1000)
        return UserData(name)
    }
}

class FakeNewsRepository(val news: List<News>) : NewsRepository {
    override suspend fun getNews(): List<News> {
        delay(1000)
        return news
    }
}

@ExperimentalCoroutinesApi
class MainViewModelTests {
    private lateinit var scheduler: TestCoroutineScheduler

    @BeforeEach
    fun setUp() {
        scheduler = TestCoroutineScheduler()
        Dispatchers.setMain(StandardTestDispatcher(scheduler))
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `user name is shown`() {
        // when
        viewModel.onCreate()
        scheduler.advanceUntilIdle()

        // then
        assertEquals(aName, viewModel.userName.value)
    }

    @Test
    fun `sorted news are shown`() {
        // when
        viewModel.onCreate()
        scheduler.advanceUntilIdle()

        // then
        val someNewsSorted =
            listOf(News(date1), News(date2), News(date3))
        assertEquals(someNewsSorted, viewModel.news.value)
    }

    @Test
    fun `user and news are called concurrently`() {
        // when
        viewModel.onCreate()
        scheduler.advanceUntilIdle()

        // then
        assertEquals(1000, scheduler.currentTime)
    }
}