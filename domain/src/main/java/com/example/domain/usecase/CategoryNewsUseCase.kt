package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.CategoryNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategoryNewsUseCase @Inject constructor(
    private val categoryNewsRepository: CategoryNewsRepository
) {

    operator fun invoke(country: String): Flow<PagingData<Article>> {
        return categoryNewsRepository.getCategoryNews(country)
            .flowOn(Dispatchers.IO)
    }
}