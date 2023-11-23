package com.example.favorite.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecases.BlogUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(blogUseCase: BlogUseCase) : ViewModel() {
    val moviesFavorite = blogUseCase.getFavorite().asLiveData()
}