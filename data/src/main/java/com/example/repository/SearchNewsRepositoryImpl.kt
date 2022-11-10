package com.example.repository

import android.util.Log
import androidx.paging.PagingData
import com.example.domain.entity.Article
import com.example.domain.repository.SearchNewsRepository
import com.example.source.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    val remote: Remote
) : SearchNewsRepository {
    override fun searchArticleNews(search: String): Flow<PagingData<Article>> {
        return remote.searchArticles(search)

        //Log.d("TAG12", "searchArticleNews: ${remote.searchArticles("apple")}")
    }

}