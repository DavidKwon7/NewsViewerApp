package com.example.repository

import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertNews(article: Article)

    suspend fun deleteNews(article: Article)

    fun getAllNews(): Flow<List<Article>>

}