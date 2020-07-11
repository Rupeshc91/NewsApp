package com.android.newsapp.api

import com.android.newsapp.model.ArticleResponse
import com.android.newsapp.model.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(PATH_SOURCES_V2)
    suspend fun getSources(): Response<SourceResponse>

    @GET(PATH_EVERYTHING_V2)
    suspend fun getNews(@Query("page") page: Int): Response<ArticleResponse>
}