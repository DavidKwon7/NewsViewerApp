package com.example.domain.usecase

import com.example.domain.entity.Article
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    fun invoke(): Flow<List<Article>> {
        return localRepository.getAllNews()
    }
}