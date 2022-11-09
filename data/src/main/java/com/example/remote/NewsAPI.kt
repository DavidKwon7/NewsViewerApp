package com.example.remote

import com.example.domain.entity.NewsResponse
import com.example.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getCategoryNews(
        @Query("country") countryCode: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("pageSize") num: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}