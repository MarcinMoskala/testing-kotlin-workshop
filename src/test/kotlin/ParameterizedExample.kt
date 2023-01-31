import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.util.*
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ParameterizedExample {

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 5, 15])
    fun `should not change values inside coercion range`(number: Int) {
        assertEquals(number, number.coerceIn(1..15))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "\n", "   "])
    fun `should accept blank values`(value: String) {
        assertTrue(value.isBlank())
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    fun isNullOrBlankTest1(value: String?) {
        assertTrue(value.isNullOrBlank())
    }

    @ParameterizedTest
    @NullAndEmptySource
    fun isNullOrBlankTest2(value: String?) {
        assertTrue(value.isNullOrBlank())
    }

    @ParameterizedTest
    @EnumSource(Letter::class)
    fun singleLetterName(letter: Letter) {
        println(letter)
    }

    enum class Letter {
        A, B, C
    }

    @ParameterizedTest
    @CsvSource("test,TEST", "tEst,TEST", "Java,JAVA")
    fun testUppercaseCsvSource(input: String, expected: String?) {
        val actualValue = input.uppercase(Locale.getDefault())
        assertEquals(expected, actualValue)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/data.csv"], numLinesToSkip = 1)
    fun testUppercaseCsvFile(input: String, expected: String?) {
        val actualValue = input.uppercase()
        assertEquals(expected, actualValue)
    }

    @ParameterizedTest
    @MethodSource("provideUppercaseQuestionsAndAnswers")
    fun testUppercaseFromProvider(input: String, expected: String?) {
        val actualValue = input.uppercase()
        assertEquals(expected, actualValue)
    }

    companion object {
        @JvmStatic
        fun provideUppercaseQuestionsAndAnswers() = listOf(
            Arguments.of("aleX", "ALEX"),
            Arguments.of("John", "JOHN"),
            Arguments.of(" s a", " S A"),
            Arguments.of("ALA", "ALA")
        )
    }

    @ParameterizedTest
    @ArgumentsSource(UppercaseArgumentsProvider::class)
    fun testUppercaseFromSource(input: String, expected: String?) {
        val actualValue = input.uppercase()
        assertEquals(expected, actualValue)
    }

    class UppercaseArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments?> = Stream.of(
            Arguments.of("aleX", "ALEX"),
            Arguments.of("John", "JOHN"),
            Arguments.of(" s a", " S A"),
            Arguments.of("ALA", "ALA")
        )
    }

    @ParameterizedTest(name = "uppercase should transform {0} to {1}")
    @MethodSource("provideUppercaseQuestionsAndAnswers")
    fun testUppercaseFromProviderNamed(input: String, expected: String?) {
        val actualValue = input.uppercase()
        assertEquals(expected, actualValue)
    }
}

