package com.example.core.domain.model

import androidx.annotation.Keep

@Keep
data class Blogs(
    val `data`: List<Blog>,
    val limit: Int,
    val page: Int,
    val total: Int
)