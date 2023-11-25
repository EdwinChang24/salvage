package io.github.edwinchang24.salvage.data

import io.github.edwinchang24.salvage.database.BookmarkDao
import io.github.edwinchang24.salvage.database.asBookmark
import io.github.edwinchang24.salvage.database.asEntity
import io.github.edwinchang24.salvage.database.asTag
import io.github.edwinchang24.salvage.model.Bookmark
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultBookmarkRepository
    @Inject
    constructor(private val bookmarkDao: BookmarkDao) : BookmarkRepository {
        override fun getBookmark(id: String) = bookmarkDao.getBookmarkEntity(id).map { it.asBookmark }

        override fun getBookmarks() = bookmarkDao.getAll().map { it.map { entity -> entity.asBookmark } }

        override fun getBookmarksWithTags() =
            bookmarkDao.getAllWithTags().map {
                it.associate { bookmarkEntityWithTags ->
                    bookmarkEntityWithTags.bookmarkEntity.asBookmark to
                        bookmarkEntityWithTags.tagEntities.map { entity -> entity.asTag }
                }
            }

        override suspend fun addBookmark(bookmark: Bookmark) = bookmarkDao.insertAll(bookmark.asEntity)

        override suspend fun deleteBookmark(bookmark: Bookmark) = bookmarkDao.delete(bookmark.asEntity)

        override suspend fun updateBookmark(bookmark: Bookmark) = bookmarkDao.update(bookmark.asEntity)
    }
