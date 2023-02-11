import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import java.util.*

@ExtendWith(ExecuteTestsOnLocalOnly::class)
class ConditionalExtensionExample {

    @Test
    @OnlyLocal
    fun testLocal() {
        println("Local test executed")
    }

    @Test
    fun test() {
        println("Test executed")
    }
}

annotation class OnlyLocal

class ExecuteTestsOnLocalOnly : ExecutionCondition {
    override fun evaluateExecutionCondition(context: ExtensionContext?): ConditionEvaluationResult {
        val env = System.getenv("env")
        val isLocalOnly: Boolean? = context
            ?.testMethod
            ?.takeIf { it.isPresent }
            ?.get()
            ?.isAnnotationPresent(OnlyLocal::class.java)
        return when {
            isLocalOnly != true -> ConditionEvaluationResult.enabled("Not local-only")
            env != "local" -> ConditionEvaluationResult.disabled("Test disabled on QA environment")
            else -> ConditionEvaluationResult.enabled("Test enabled on QA environment")
        }
    }
}