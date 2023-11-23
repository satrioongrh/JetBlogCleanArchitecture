package com.example.jetblogca.screen.home

import com.example.core.domain.model.Blog

data class HomeState (
    var isLoading: Boolean = false,
    var data: List<Blog>? = null,
    var error: String = ""
)