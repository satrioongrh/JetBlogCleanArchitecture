package com.example.jetblogca.di

import com.example.core.domain.usecases.BlogInteractor
import com.example.core.domain.usecases.BlogUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideBlogUseCase(blogInteractor: BlogInteractor): BlogUseCase
}