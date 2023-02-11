import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler
import java.io.FileNotFoundException

@ExtendWith(ExceptionHandlingExtension::class)
class ExceptionHandlingExtensionExample {
    @Test
    fun test() {
        throw IllegalArgumentException("Illegal argument")
    }
}

class ExceptionHandlingExtension : TestExecutionExceptionHandler {
    override fun handleTestExecutionException(
        context: ExtensionContext,
        throwable: Throwable
    ) {
        println("There was exception $throwable")
        throw throwable
    }
}