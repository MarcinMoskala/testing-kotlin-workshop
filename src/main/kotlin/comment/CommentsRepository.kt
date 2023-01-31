package domain.comment

import java.time.Instant

interface CommentsRepository {
    suspend fun getComments(collectionKey: String): List<CommentDocument>
    suspend fun getComment(id: String): CommentDocument?
    suspend fun addComment(comment: CommentDocument)
    suspend fun deleteComment(commentId: String)
}

data class CommentDocument(
    val _id: String,
    val collectionKey: String,
    val userId: String,
    val comment: String?,
    val date: Instant,
)

data class CommentsCollection(
    val collectionKey: String,
    val elements: List<CommentElement>,
)

data class CommentElement(
    val id: String,
    val collectionKey: String,
    val user: User?,
    val comment: String?,
    val date: Instant,
)

data class AddComment(
    val comment: String?,
)