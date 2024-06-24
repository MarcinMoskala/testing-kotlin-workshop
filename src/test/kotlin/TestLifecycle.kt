import org.junit.jupiter.api.*
import kotlin.test.Ignore
import kotlin.test.assertEquals

class TestLifecycle {

    @BeforeEach
    fun setup() {
        println("@BeforeEach executed")
    }

    @AfterEach
    fun tearDown() {
        println("@AfterEach executed")
    }

    @Test
    fun testCalcOne() {
        println("======TEST ONE EXECUTED=======")
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCalcTwo() {
        println("======TEST TWO EXECUTED=======")
        assertEquals(6, 2 + 4)
    }

    @Ignore
    @Test
    fun ignoredTest() {
        println("======IGNORED EXECUTED=======")
        assertEquals(6, 2 + 4)
    }

    @Nested
    inner class InnerClass {
        @Test
        fun testCalcThree() {
            println("======TEST THREE EXECUTED=======")
            assertEquals(6, 2 + 4)
        }

        @Test
        fun testCalcFour() {
            println("======TEST FOUR EXECUTED=======")
            assertEquals(6, 2 + 4)
        }
    }

    @TestFactory
    fun testsFiveAndSix(): List<DynamicTest> = List(100) { "Name$it" }
        .map { name ->
            DynamicTest.dynamicTest("testCalc${name.replaceFirstChar { it.titlecase() }}") {
                println("======TEST ${name.uppercase()} EXECUTED=======")
                assertEquals(15, Math.addExact(5, 10))
            }
        }
    
    @TestFactory
    fun `square should produce valid result for small numbers`(): List<DynamicTest> = mapOf(
        1 to 1,
        2 to 4,
        3 to 9,
        4 to 16
    ).map { (num, expectedResult) ->
        DynamicTest.dynamicTest("Square of $num should be $expectedResult") {
            assertEquals(expectedResult, i(num))
        }
    }

    private fun i(num: Int) = num * num


    companion object {
        @JvmStatic
        @BeforeAll
        fun setupAll() {
            println("@BeforeAll executed")
        }

        @JvmStatic
        @AfterAll
        fun tearDownAll() {
            println("@AfterAll executed")
        }
    }
}

