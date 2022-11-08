package com.example.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.remote.NewsAPI
import com.example.repository.SearchPagingSource
import javax.inject.Inject
import kotlin.math.max

class Remote @Inject constructor(
    val api: NewsAPI
) {

    fun searchArticles(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(api, query) }
        ).flow
}