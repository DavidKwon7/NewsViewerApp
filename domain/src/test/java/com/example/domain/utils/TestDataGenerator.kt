package com.example.domain.utils

import com.example.domain.entity.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf

class TestDataGenerator {

    companion object {
        fun generateArticle(): Flow<List<Article>> {
            val item1 = Article(
                "author1",
                "content1",
                "description1",
                "2022",
                "title1",
                "http1",
                "image1",
                1
            )

            val item2 = Article(
                "author2",
                "content2",
                "description2",
                "2022",
                "title2",
                "http2",
                "image2",
                2
            )

            val item3 = Article(
                "author3",
                "content3",
                "description3",
                "2022",
                "title3",
                "http3",
                "image3",
                3
            )
            return flowOf(listOf(item1))
        }
    }
}