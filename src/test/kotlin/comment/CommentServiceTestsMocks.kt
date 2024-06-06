@file:OptIn(ExperimentalCoroutinesApi::class)

package comment

import domain.comment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.Instant

class CommentServiceTestsMocks {

    @Nested
    inner class AddComment {
        @Test
        fun `Should add comment`(): Unit = runTest {

        }

        @Test
        fun `Should give next ids to consecutive comments`(): Unit = runTest {

        }

        @Test
        fun `Should specify correct creation time`(): Unit = runTest {

        }
    }

    @Nested
    inner class GetComments {
        @Test
        fun `Should get comments by collection key`(): Unit = runTest {

        }
    }

    @Nested
    inner class DeleteComment {
        @Test
        fun `Should delete comment by id`(): Unit = runTest {

        }

        @Test
        fun `Should throw exception when comment does not exist`(): Unit = runTest {

        }

        @Test
        fun `Should throw exception when user is not an owner`(): Unit = runTest {

        }

        @Test
        fun `Should concurrently find users when getting comments`(): Unit = runTest {
            // We will do that later
        }
    }

    // Fake Data
    private val aToken = "SOME_TOKEN"
    private val collectionKey1 = "SOME_COLLECTION_KEY_1"
    private val collectionKey2 = "SOME_COLLECTION_KEY_2"
    private val date1 = Instant.parse("2018-11-30T18:35:24.00Z")
    private val date2 = Instant.parse("2019-11-30T18:35:24.00Z")
    private val user1 = User(
        id = "U_ID_1",
        email = "user1@email.com",
        imageUrl = "some_image_1",
        displayName = "some_display_name_1",
        bio = "some bio 1"
    )
    private val user2 = User(
        id = "U_ID_2",
        email = "user2@email.com",
        imageUrl = "some_image_2",
        displayName = "some_display_name_2",
        bio = "some bio 2"
    )
    private val commentDocument1 = CommentDocument(
        _id = "C_ID_1",
        collectionKey = collectionKey1,
        userId = user1.id,
        comment = "Some comment 1",
        date = date1,
    )
    private val commentDocument2 = CommentDocument(
        _id = "C_ID_2",
        collectionKey = collectionKey2,
        userId = user2.id,
        comment = "Some comment 2",
        date = date2,
    )
    private val commentDocument3 = CommentDocument(
        _id = "C_ID_3",
        collectionKey = collectionKey1,
        userId = user2.id,
        comment = "Some comment 3",
        date = date2,
    )
    private val commentElement1 = CommentElement(
        id = "C_ID_1",
        collectionKey = collectionKey1,
        user = user1,
        comment = "Some comment 1",
        date = date1,
    )
    private val commentElement2 = CommentElement(
        id = "C_ID_2",
        collectionKey = collectionKey2,
        user = user2,
        comment = "Some comment 2",
        date = date2,
    )
    private val commentElement3 = CommentElement(
        id = "C_ID_3",
        collectionKey = collectionKey1,
        user = user2,
        comment = "Some comment 3",
        date = date2,
    )
}
