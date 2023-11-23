package com.example.core.Utils

import androidx.room.TypeConverter
import com.example.core.data.local.entity.OwnerEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }
}

class OwnerEntityConverter {
    @TypeConverter
    fun fromOwnerEntity(owner: OwnerEntity): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun toOwnerEntity(ownerString: String): OwnerEntity {
        return Gson().fromJson(ownerString, OwnerEntity::class.java)
    }
}
