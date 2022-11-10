package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface CategoryNewsRepository {

    fun getCategoryNews(country: String): Flow<PagingData<Article>>
}