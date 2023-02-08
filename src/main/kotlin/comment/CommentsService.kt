@file:Suppress("ComplexRedundantLet")

package domain.comment

import kotlinx.coroutines.coroutineScope
import mapAsync

class CommentsService(
    private val commentsRepository: CommentsRepository,
    private val userService: UserService,
    private val commentFactory: CommentFactory
) {
    suspend fun addComment(token: String, collectionKey: String, body: AddComment) {
        userService.findUser(token)
            .let { user -> commentFactory.toCommentDocument(user.id, collectionKey, body) }
            .also { commentsRepository.addComment(it) }
    }

    suspend fun getComments(collectionKey: String): CommentsCollection {
        return commentsRepository.getComments(collectionKey)
            .let {
                CommentsCollection(
                    collectionKey = collectionKey,
                    elements = it.mapAsync(::makeCommentElement)
                )
            }
    }

    suspend fun deleteComment(token: String, commentId: String) = coroutineScope {
        val userId = userService.readUserId(token)

        val comment = commentsRepository.getComment(commentId)
        requireNotNull(comment) { "Comment does not exist" }
        require(comment.userId == userId) { "Not an owner" }

        commentsRepository.deleteComment(commentId)
    }

    private suspend fun makeCommentElement(commentDocument: CommentDocument) = CommentElement(
        id = commentDocument._id,
        collectionKey = commentDocument.collectionKey,
        user = userService.findUserById(commentDocument.userId),
        comment = commentDocument.comment,
        date = commentDocument.date,
    )
}