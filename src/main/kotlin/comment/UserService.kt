package domain.comment

interface UserService {
    fun readUserId(token: String): String
    suspend fun findUser(token: String): User
    suspend fun findUserById(id: String): User
}

object NoSuchUserException: Exception("No such user")

data class User(
    val id: String,
    val email: String,
    val imageUrl: String,
    val displayName: String? = null,
    val bio: String? = null,
)