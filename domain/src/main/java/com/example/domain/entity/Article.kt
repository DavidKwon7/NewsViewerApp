package com.example.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nullable

@Entity(
    tableName = "articleEntity"
)
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //val source: Source,
    val title: String,
    @Nullable val url: String?,
    @Nullable val urlToImage: String? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null
) : java.io.Serializable
