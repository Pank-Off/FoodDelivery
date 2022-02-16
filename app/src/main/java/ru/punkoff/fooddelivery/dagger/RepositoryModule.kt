package ru.punkoff.fooddelivery.dagger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.punkoff.fooddelivery.repository.Repository
import ru.punkoff.fooddelivery.repository.RepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}