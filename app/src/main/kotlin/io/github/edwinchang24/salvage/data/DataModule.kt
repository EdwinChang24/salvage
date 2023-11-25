package io.github.edwinchang24.salvage.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsBookmarkRepository(bookmarkRepository: DefaultBookmarkRepository): BookmarkRepository

    @Binds
    fun bindsTagRepository(tagRepository: DefaultTagRepository): TagRepository
}
