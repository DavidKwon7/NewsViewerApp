package com.example.domain.utils

import com.example.domain.entity.Article

class TestDataGenerator {

    companion object {
        fun generateArticle(): List<Article> {
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
            return listOf(item1, item2, item3)
        }
    }
}