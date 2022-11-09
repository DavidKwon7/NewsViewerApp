package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository
) {
    operator fun invoke(params: String): Flow<PagingData<Article>> {
        return searchNewsRepository.searchArticleNews(params)
            .flowOn(Dispatchers.IO) // 일단 넣어봄..
    }
}