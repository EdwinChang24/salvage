package io.github.edwinchang24.salvage.data

import io.github.edwinchang24.salvage.database.TagDao
import io.github.edwinchang24.salvage.database.asBookmark
import io.github.edwinchang24.salvage.database.asEntity
import io.github.edwinchang24.salvage.database.asTag
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultTagRepository
    @Inject
    constructor(private val tagDao: TagDao) : TagRepository {
        override fun getTag(id: String) = tagDao.getTagEntity(id).map { it.asTag }

        override fun getTags(): Flow<List<Tag>> = tagDao.getAll().map { it.map { tagEntity -> tagEntity.asTag } }

        override fun getTagsWithBookmarks() =
            tagDao.getAllWithBookmarks().map {
                it.associate { tagEntityWithBookmarks ->
                    tagEntityWithBookmarks.tagEntity.asTag to
                        tagEntityWithBookmarks.bookmarkEntities.map { entity -> entity.asBookmark }
                }
            }

        override suspend fun addTag(tag: Tag) = tagDao.insertAll(tag.asEntity)

        override suspend fun deleteTag(tag: Tag) = tagDao.delete(tag.asEntity)

        override suspend fun updateTag(tag: Tag) = tagDao.update(tag.asEntity)
    }
