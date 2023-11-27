package com.example.core.data.network.model

import androidx.annotation.Keep

@Keep
data class OwnerDTO(
    val firstName: String?,
    val id: String?,
    val lastName: String?,
    val picture: String?,
    val title: String?
)