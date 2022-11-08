package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewUseCase @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository
) {
    suspend fun invoke(params: String): Flow<PagingData<Article>> {
        return searchNewsRepository.searchArticleNews(params)
    }
}