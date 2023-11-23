package com.example.core.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class OwnerEntity(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
)