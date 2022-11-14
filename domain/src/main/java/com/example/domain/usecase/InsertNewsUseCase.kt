package com.example.domain.usecase

import com.example.domain.entity.Article
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertNewsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun invoke(article: Article): Flow<List<Article>> {
        return localRepository.insertNews(article)
    }
}