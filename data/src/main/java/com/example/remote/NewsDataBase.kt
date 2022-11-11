package com.example.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entity.Article
import com.example.local.NewsDao

@Database(
    entities = [Article::class],
    version = 1
)

abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}