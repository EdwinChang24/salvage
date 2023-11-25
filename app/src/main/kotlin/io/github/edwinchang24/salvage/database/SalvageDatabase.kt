package io.github.edwinchang24.salvage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BookmarkEntity::class, TagEntity::class, BookmarkTagCrossRefEntity::class],
    version = 1,
    autoMigrations = [],
    exportSchema = true
)
@TypeConverters(InstantConverter::class)
abstract class SalvageDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    abstract fun tagDao(): TagDao
}
