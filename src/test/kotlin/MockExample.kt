import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.time.Instant
import java.util.*
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockExample {

    interface TimeProvider {
        fun now(): Instant
    }

    @Test
    fun interfaceMocking() {
        val timeProvider: TimeProvider = mockk()
        every { timeProvider.now() } returns Instant.parse("2007-12-20T10:15:30.00Z")

        val result = timeProvider.now()

        assertEquals(Instant.parse("2007-12-20T10:15:30.00Z"), result)
    }

    class UuidProvider {
        fun random(): String = UUID.randomUUID().toString()
    }

    @Test
    fun classMocking() {
        val uuidProvider: UuidProvider = mockk()
        every { uuidProvider.random() } returns "SOME_UUID"

        val result = uuidProvider.random()

        assertEquals("SOME_UUID", result)
    }

    @Test
    fun functionMocking() {
        mockkStatic(::magicNumber)
        every { magicNumber() } returns 42

        val result = magicNumber()

        assertEquals(42, result)
    }

    interface CommentRepository {
        fun addComment(id: String, comment: String, creationTime: Instant, magicNumber: Int)
    }

    class CommentService(
        private val timeProvider: TimeProvider,
        private val uuidProvider: UuidProvider,
        private val commentRepository: CommentRepository
    ) {
        fun add(comment: String) {
            commentRepository.addComment(
                id = uuidProvider.random(),
                comment = comment,
                creationTime = timeProvider.now(),
                magicNumber = magicNumber()
            )
        }
    }

    val date1 = Instant.parse("2007-12-20T10:15:30.00Z")
    val aUuid = "SOME_UUID"
    val aComment = "Some comment"

    @Test
    fun testingUserService1() {
        // given
        val timeProvider: TimeProvider = mockk()
        every { timeProvider.now() } returns date1

        val uuidProvider: UuidProvider = mockk()
        every { uuidProvider.random() } returns aUuid

//        val commentRepository: CommentRepository = mockk()
//        every { commentRepository.addComment(any(), any(), any(), any()) } just runs
        val commentRepository: CommentRepository = mockk(relaxed = true)

        mockkStatic(::magicNumber)
        every { magicNumber() } returns 42

        val service = CommentService(
            timeProvider = timeProvider,
            uuidProvider = uuidProvider,
            commentRepository = commentRepository
        )

        // when
        service.add(aComment)

        // then
        verify {
            commentRepository.addComment(aUuid, aComment, date1, 42)
        }
    }

    private val timeProvider: TimeProvider = mockk(relaxed = true)
    private val uuidProvider: UuidProvider = mockk(relaxed = true)
    private val commentRepository: CommentRepository = mockk(relaxed = true)
    private val commentService = CommentService(timeProvider, uuidProvider, commentRepository)

    @AfterEach
    fun cleanup() {
        clearAllMocks()
    }

    @Test
    fun testingUserService2() {
        // given
        every { timeProvider.now() } returns date1
        every { uuidProvider.random() } returns aUuid
        mockkStatic(::magicNumber)
        every { magicNumber() } returns 42

        // when
        commentService.add(aComment)

        // then
        verify {
            commentRepository.addComment(aUuid, aComment, date1, 42)
        }
    }

    @Test
    fun testingUserService3() {
        // given
        val commentRepository = mockk<CommentRepository>(relaxed = true)
        val commentService = CommentService(
            mockk {
                every { now() } returns date1
            },
            mockk {
                every { random() } returns aUuid

            },
            commentRepository
        )
        mockkStatic(::magicNumber)
        every { magicNumber() } returns 42

        // when
        commentService.add(aComment)

        // then
        verify {
            commentRepository.addComment(aUuid, aComment, date1, 42)
        }
    }

    @Test
    fun `should use random uuid`() {
        // given
        every { timeProvider.now() } returns date1
        every { uuidProvider.random() } returns aUuid
        mockkStatic(::magicNumber)
        every { magicNumber() } returns 42

        // when
        commentService.add(aComment)

        // then
        val slot = slot<String>()
        verify {
            commentRepository.addComment(capture(slot), any(), any(), any())
        }
        assertEquals(aUuid, slot.captured)
    }
}

// top-level function
fun magicNumber() = 7

