package io.github.edwinchang24.salvage.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesBookmarkDao(database: SalvageDatabase) = database.bookmarkDao()

    @Provides
    fun providesTagDao(database: SalvageDatabase) = database.tagDao()
}
