package com.example.core.data.network.model

import androidx.annotation.Keep

@Keep
data class BlogsDTO(
    val `data`: List<BlogDTO>?,
    val limit: Int?,
    val page: Int?,
    val total: Int?
)