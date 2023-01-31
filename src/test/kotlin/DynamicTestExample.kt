import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class DynamicTestExample {
    @TestFactory
    fun testsFiveAndSix(): List<DynamicTest> = mapOf(
        1 to 1,
        2 to 4,
        3 to 9,
        4 to 16
    ).map { (num, expectedResult) ->
            DynamicTest.dynamicTest("testSquare$num") {
                assertEquals(expectedResult, num * num)
            }
        }
}