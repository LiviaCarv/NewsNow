package com.project.newsnow.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.newsnow.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(ArticleTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}