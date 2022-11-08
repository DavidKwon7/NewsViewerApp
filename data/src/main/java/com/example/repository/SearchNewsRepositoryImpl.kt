package com.example.repository

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import com.example.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    val remote: Remote
) : SearchNewsRepository {
    override suspend fun searchArticleNews(search: String): Flow<PagingData<Article>> {
        return remote.searchArticles(search)
    }
}