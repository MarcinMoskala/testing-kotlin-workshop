import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(TestExtension::class)
class LifecycleExtensionsExample {
    @JvmField
    @RegisterExtension
    var extension = TestExtension("custom")

    @Test
    fun test() {
        println("Actual test (param is ${extension.valueToSetup})")
    }
}

class TestExtension(
    val param: String = "default"
) : BeforeEachCallback, AfterEachCallback, BeforeAllCallback, AfterAllCallback {
    lateinit var valueToSetup: String

    override fun beforeAll(context: ExtensionContext?) {
        println("Calling beforeAll from TestExtension1 with $param")
    }

    override fun beforeEach(context: ExtensionContext?) {
        println("Calling beforeEach from TestExtension1 with $param")
        valueToSetup = "Done"
    }

    override fun afterEach(context: ExtensionContext?) {
        println("Calling afterEach from TestExtension1 with $param")
    }

    override fun afterAll(context: ExtensionContext?) {
        println("Calling afterAll from TestExtension1 with $param")
    }
}