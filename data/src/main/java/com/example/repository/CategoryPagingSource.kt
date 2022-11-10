package com.example.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.entity.Article
import com.example.remote.NewsAPI
import com.example.util.Constants.Companion.PAGING_START_PAGE
import retrofit2.HttpException
import java.io.IOException

class CategoryPagingSource(
    private val api: NewsAPI,
    private val country: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: PAGING_START_PAGE
        return try {
            val response = api.getCategoryNews(
                country, position, params.loadSize
            )
            val article = response.articles
            LoadResult.Page(
                data = article,
                prevKey = if (position == PAGING_START_PAGE) null else position - 1,
                nextKey = if (article.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}