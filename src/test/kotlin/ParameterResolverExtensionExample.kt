import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

@ExtendWith(EmployeeDaoParameterResolver::class)
class ParameterResolverExtensionExample {
    @Test
    fun testUser(user: User) {
        println("Received $user")
    }
}

class EmployeeDaoParameterResolver : ParameterResolver {
    override fun supportsParameter(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext
    ): Boolean = parameterContext.parameter.type == User::class.java

    override fun resolveParameter(
        parameterContext: ParameterContext,
        extensionContext: ExtensionContext
    ): Any = User("test user")
}