package com.example.jetblogca.di

import com.example.core.domain.usecases.BlogUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun blogUseCase(): BlogUseCase
}