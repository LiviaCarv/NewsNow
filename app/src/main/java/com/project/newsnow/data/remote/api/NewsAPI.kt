package com.project.newsnow.data.remote.api

import com.project.newsnow.BuildConfig
import com.project.newsnow.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : NewsResponse
}