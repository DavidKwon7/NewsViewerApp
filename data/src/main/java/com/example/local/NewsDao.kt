package com.example.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(article: Article)

    @Query("SELECT * FROM articleEntity")
    fun getAllNews(): Flow<List<Article>>

    @Delete
    suspend fun deleteNews(article: Article)

}