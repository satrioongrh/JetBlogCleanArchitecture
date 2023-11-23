package com.example.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.core.Utils.OwnerEntityConverter
import com.example.core.Utils.StringListConverter

@TypeConverters(StringListConverter::class, OwnerEntityConverter::class)
@Entity(tableName = "blog")
data class BlogEntity (

    @PrimaryKey(autoGenerate = false)
    var id: String,

    val image: String,

    val likes: Int,

    val owner: OwnerEntity,

    val publishDate: String,

    val tags: List<String>,

    val text: String,

    var isFavorite: Boolean = false
)