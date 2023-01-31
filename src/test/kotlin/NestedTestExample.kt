import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NestedTestExample {
    @Nested
    inner class Inner1 {
        @Test
        fun inner1Test1() {
            // ...
        }

        @Test
        fun inner1Test2() {
            // ...
        }
    }
    @Nested
    inner class Inner2 {
        @Test
        fun inner2Test1() {
            // ...
        }

        @Test
        fun inner2Test2() {
            // ...
        }
    }
}