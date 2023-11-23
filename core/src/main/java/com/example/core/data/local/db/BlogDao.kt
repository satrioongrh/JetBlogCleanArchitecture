package com.example.core.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.local.entity.BlogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BlogDao {

    @Query("SELECT * FROM blog")
    fun getAllBlog() : Flow<List<BlogEntity>>

    @Query("SELECT * FROM blog WHERE isFavorite = 1")
    fun getFavoriteBlog() : Flow<List<BlogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBlogs(blogs: List<BlogEntity>)

    @Update
    fun updateFavoriteBlogs(blog: BlogEntity)

}