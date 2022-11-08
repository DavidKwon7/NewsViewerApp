package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface SearchNewsRepository {

    suspend fun searchArticleNews(Search: String): Flow<PagingData<Article>>
}