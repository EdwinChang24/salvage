package io.github.edwinchang24.salvage.data

import io.github.edwinchang24.salvage.model.Bookmark
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmark(id: String): Flow<Bookmark>

    fun getBookmarks(): Flow<List<Bookmark>>

    fun getBookmarksWithTags(): Flow<Map<Bookmark, List<Tag>>>

    suspend fun addBookmark(bookmark: Bookmark)

    suspend fun deleteBookmark(bookmark: Bookmark)

    suspend fun updateBookmark(bookmark: Bookmark)
}
