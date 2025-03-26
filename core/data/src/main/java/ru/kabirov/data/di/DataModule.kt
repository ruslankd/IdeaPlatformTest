package ru.kabirov.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kabirov.database.GoodsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGoodsDatabase(
        @ApplicationContext context: Context
    ): GoodsDatabase {
        return GoodsDatabase(context)
    }
}