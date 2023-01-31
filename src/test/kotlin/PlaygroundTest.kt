import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import kotlin.test.*

class PlaygroundTest {

    @Test
    fun example() {
        val str1: String = "ABC"
        val str2: String? = null
        val str3: String = "DEF"
        val strs = listOf(str1, "GHI")
        assertAll(
            { assertEquals("ABC", str1) },
            { assertContains(strs, str1) },
            { assertContains(strs, str2) },
            { assertNotNull(str2) },
            { assertEquals(str2, str1) },
            { assertEquals(strs, listOf(str1)) },
            { assertNotNull(str1) },
            { assertThrows<AssertionError> { assertEquals(4, 2 + 3) } },
            { assertThrows<AssertionError> { assertEquals(4, 2 + 2) } },
            { assertNull(str1) },
            { assertEquals(4, 2 + 2) },
            { assertDoesNotThrow { assertEquals(4, 2 + 3) } },
        )
    }
}

