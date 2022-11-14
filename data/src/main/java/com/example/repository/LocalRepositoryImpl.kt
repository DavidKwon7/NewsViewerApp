package com.example.repository

import com.example.domain.entity.Article
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {

    override suspend fun insertNews(article: Article) {
        return localDataSource.insertNews(article)
    }

    override suspend fun deleteNews(article: Article) {
        return localDataSource.deleteNews(article)
    }

    override fun getAllNews(): Flow<List<Article>> {
        return localDataSource.getAllNews()
    }
}