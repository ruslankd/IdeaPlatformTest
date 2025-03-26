package ru.kabirov.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.kabirov.data.api.GoodsRepository
import ru.kabirov.data.impl.GoodsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGoodsRepository(
        ipAddressRepositoryImpl: GoodsRepositoryImpl,
    ): GoodsRepository
}