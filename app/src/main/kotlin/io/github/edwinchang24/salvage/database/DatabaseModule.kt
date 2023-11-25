package io.github.edwinchang24.salvage.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesSalvageDatabase(
        @ApplicationContext context: Context
    ) = Room
        .databaseBuilder(context, SalvageDatabase::class.java, "salvage-database")
        .build()
}
