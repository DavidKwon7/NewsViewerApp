package com.example.repository

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.CategoryNewsRepository
import com.example.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryNewsRepositoryImpl @Inject constructor(
    private val remote: Remote
) : CategoryNewsRepository {
    override fun getCategoryNews(country: String): Flow<PagingData<Article>> {
        return remote.getArticlesNews(country)
    }
}