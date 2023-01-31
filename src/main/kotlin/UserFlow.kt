import kotlinx.coroutines.flow.Flow

data class User(val name: String)

interface UserRepository {
    fun takePage(pageNumber: Int): List<User>
}

fun makeUsersFlow(repository: UserRepository): Flow<User> = TODO()