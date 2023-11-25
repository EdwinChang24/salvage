package io.github.edwinchang24.salvage.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert
    fun insertAll(vararg bookmarks: BookmarkEntity)

    @Update
    fun update(vararg bookmarks: BookmarkEntity)

    @Delete
    fun delete(vararg bookmarks: BookmarkEntity)

    @Query("""SELECT * FROM bookmarks WHERE bookmarkId = :id""")
    fun getBookmarkEntity(id: String): Flow<BookmarkEntity>

    @Query("""SELECT * FROM bookmarks""")
    fun getAll(): Flow<List<BookmarkEntity>>

    @Transaction
    @Query("""SELECT * FROM bookmarks""")
    fun getAllWithTags(): Flow<List<BookmarkEntityWithTags>>
}

@Dao
interface TagDao {
    @Insert
    fun insertAll(vararg tags: TagEntity)

    @Update
    fun update(vararg tags: TagEntity)

    @Delete
    fun delete(vararg tags: TagEntity)

    @Query("""SELECT * FROM tags WHERE tagId = :id""")
    fun getTagEntity(id: String): Flow<TagEntity>

    @Query("""SELECT * FROM tags""")
    fun getAll(): Flow<List<TagEntity>>

    @Transaction
    @Query("""SELECT * FROM tags""")
    fun getAllWithBookmarks(): Flow<List<TagEntityWithBookmarks>>
}
