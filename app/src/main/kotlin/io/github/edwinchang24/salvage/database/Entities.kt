package io.github.edwinchang24.salvage.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.github.edwinchang24.salvage.model.Bookmark
import io.github.edwinchang24.salvage.model.Tag
import kotlinx.datetime.Instant

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val bookmarkId: String,
    val name: String?,
    val url: String,
    val description: String?,
    val timeAdded: Instant,
    val timePublished: Instant?
)

val BookmarkEntity.asBookmark get() = Bookmark(bookmarkId, name, url, description, timeAdded, timePublished)
val Bookmark.asEntity get() = BookmarkEntity(bookmarkId, name, url, description, timeAdded, timePublished)

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey val tagId: String,
    val name: String,
    val color: Int,
    val description: String?
)

val TagEntity.asTag get() = Tag(tagId, name, color, description)
val Tag.asEntity get() = TagEntity(tagId, name, color, description)

@Entity(
    tableName = "bookmarks_tags_cross_refs",
    primaryKeys = ["bookmarkId", "tagId"],
    indices = [Index("bookmarkId"), Index("tagId")]
)
data class BookmarkTagCrossRefEntity(
    val bookmarkId: String,
    val tagId: String
)

data class BookmarkEntityWithTags(
    @Embedded val bookmarkEntity: BookmarkEntity,
    @Relation(
        parentColumn = "bookmarkId",
        entityColumn = "tagId",
        associateBy = Junction(BookmarkTagCrossRefEntity::class)
    )
    val tagEntities: List<TagEntity>
)

data class TagEntityWithBookmarks(
    @Embedded val tagEntity: TagEntity,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "bookmarkId",
        associateBy = Junction(BookmarkTagCrossRefEntity::class)
    )
    val bookmarkEntities: List<BookmarkEntity>
)
