package com.example.repository

import com.example.domain.entity.Article
import com.example.local.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : LocalDataSource {

    override suspend fun insertNews(article: Article): Long {
        return newsDao.insertNews(article)
    }

    override suspend fun deleteNews(article: Article) {
        return newsDao.deleteNews(article)
    }

    override fun getAllNews(): Flow<List<Article>> {
        return  newsDao.getAllNews()
    }


}