package com.example.domain.usecase

import com.example.domain.entity.Article
import com.example.domain.repository.LocalRepository
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun invoke(article: Article) {
        localRepository.deleteNews(article)

    }
}