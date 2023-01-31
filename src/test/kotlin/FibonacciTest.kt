import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class FibonacciTest {
    @Nested
    inner class Parametrized {
        @Test
        // Parametrize using CSV Literals with numbers from 0 to 10, and answer
        fun `sample values tests (CSV)`() {
            // Assert that result is as expected
        }

        @Test
        // Parametrize using method source with numbers from 0 to 10, and answer
        fun `sample values tests (method)`() {
            // Assert that result is as expected
        }

        @Test
        // Parametrize using class source with numbers from 0 to 10, and answer
        fun `sample values tests (class)`() {
            // Assert that result is as expected
        }

        @Test
        // Parametrize with some big numbers
        fun `should work for big values as well`() {
            // Assert that result does not throw and is positive
        }

        @Test
        // Parametrize with some negative numbers
        fun `should throw exception for values smaller than 0`() {
            // Assert that call throws IllegalArgumentException with appropriate message
        }
    }

    @Nested
    inner class Dynamic {
        @TestFactory
        // Parametrize with numbers from 0 to 10, and answer
        fun `sample values tests`() = listOf<DynamicTest>()

        @TestFactory
        fun `should work for big values as well`() = listOf<DynamicTest>()

        @TestFactory
        fun `should throw exception for values smaller than 0`() = listOf<DynamicTest>()
    }
}