package com.example.core.domain.usecases

import com.example.core.data.source.Resource
import com.example.core.domain.model.Blog
import kotlinx.coroutines.flow.Flow

interface BlogUseCase {

    suspend fun getAllBlog() : Flow<Resource<List<Blog>>>

    fun getFavorite(): Flow<List<Blog>>
    fun setFavorite(blog: Blog, state: Boolean)

}