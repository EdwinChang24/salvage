package io.github.edwinchang24.salvage.data

import io.github.edwinchang24.salvage.model.Bookmark
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun getTag(id: String): Flow<Tag>

    fun getTags(): Flow<List<Tag>>

    fun getTagsWithBookmarks(): Flow<Map<Tag, List<Bookmark>>>

    suspend fun addTag(tag: Tag)

    suspend fun deleteTag(tag: Tag)

    suspend fun updateTag(tag: Tag)
}
