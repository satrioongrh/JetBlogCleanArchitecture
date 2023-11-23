package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
) : Parcelable