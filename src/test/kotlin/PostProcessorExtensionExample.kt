import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestInstancePostProcessor

class PostProcessorExtensionExample: ExtendWithExample {
    override lateinit var valueToSetup: String

    @Test
    fun test() {
        println("Actual test (param is $valueToSetup)")
    }
}

@ExtendWith(PostProcessorExampleExtension::class)
interface ExtendWithExample {
    var valueToSetup: String
}

class PostProcessorExampleExtension : TestInstancePostProcessor {

    override fun postProcessTestInstance(testInstance: Any?, context: ExtensionContext?) {
        (testInstance as? ExtendWithExample)?.let { coroutineTest ->
            coroutineTest.valueToSetup = "Done"
        }
    }
}