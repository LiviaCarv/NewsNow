package com.project.newsnow.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.project.newsnow.domain.model.Source

@ProvidedTypeConverter
class ArticleTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(text: String): Source {
        val split = text.split(",")
        return Source(split[0], split[1])

    }
}