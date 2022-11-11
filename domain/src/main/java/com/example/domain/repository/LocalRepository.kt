package com.example.domain.repository

import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertNews(article: Article): Long

    suspend fun deleteNews(article: Article)

    fun getAllNews(): Flow<List<Article>>
}