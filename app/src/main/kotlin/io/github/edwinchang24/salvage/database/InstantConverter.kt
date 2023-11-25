package io.github.edwinchang24.salvage.database

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?) = value?.let { Instant.fromEpochMilliseconds(it) }

    @TypeConverter
    fun instantToLong(value: Instant?) = value?.toEpochMilliseconds()
}
