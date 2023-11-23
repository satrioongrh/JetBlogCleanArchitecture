package com.example.core.data.local

import com.example.core.data.local.db.BlogDao
import com.example.core.data.local.entity.BlogEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val blogDao: BlogDao) {
    fun getAllBlogs(): Flow<List<BlogEntity>> = blogDao.getAllBlog()
    fun getFavorite(): Flow<List<BlogEntity>> = blogDao.getFavoriteBlog()
    fun insertBlog(blogList: List<BlogEntity>) = blogDao.insertAllBlogs(blogList)
    fun setFavoriteBlogs(blogs: BlogEntity, newState: Boolean) {
        blogs.isFavorite = newState
        blogDao.updateFavoriteBlogs(blogs)
    }
}