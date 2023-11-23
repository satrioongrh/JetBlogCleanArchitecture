package com.example.jetblogca.screen.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Blog
import com.example.core.domain.usecases.BlogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val blogUseCase: BlogUseCase) : ViewModel() {
    fun setFavoriteBlog(blog: Blog, newStatus: Boolean) {
        blogUseCase.setFavorite(blog, newStatus)
    }
}