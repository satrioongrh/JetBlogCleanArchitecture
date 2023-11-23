package com.example.core.domain.usecases

import com.example.core.data.source.Resource
import com.example.core.domain.model.Blog
import com.example.core.domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlogInteractor @Inject constructor(private val blogRepository: BlogRepository) : BlogUseCase {
    override suspend fun getAllBlog(): Flow<Resource<List<Blog>>> {
        return blogRepository.getAllBlogs()
    }

    override fun getFavorite(): Flow<List<Blog>> {
        return blogRepository.getFavorite()
    }

    override fun setFavorite(blog: Blog, state: Boolean) {
        return blogRepository.setFavorite(blog, state)
    }
}