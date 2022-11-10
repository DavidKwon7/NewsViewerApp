package com.example.domain.entity

import javax.annotation.Nullable

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //val source: Source,
    val title: String,
    val url: String?,
    @Nullable val urlToImage: String? = null,
    val id: Long? = null
)
