package com.example.core.data.network.model

import androidx.annotation.Keep

@Keep
data class BlogDTO(
    val id: String?,
    val image: String?,
    val likes: Int?,
    val owner: OwnerDTO?,
    val publishDate: String?,
    val tags: List<String>?,
    val text: String?
)