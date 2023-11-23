package com.example.core.di

import com.example.core.data.source.BlogRepositoryImpl
import com.example.core.domain.repository.BlogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, DataModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(blogRepository: BlogRepositoryImpl) : BlogRepository

}