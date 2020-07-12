package com.android.newsapp.data

import com.android.newsapp.api.Result
import com.android.newsapp.model.ArticleResponse
import com.android.newsapp.model.SourceResponse

interface NewsRepository {

    suspend fun getSources(): Result<SourceResponse>

    suspend fun getNews(page: Int, source: String): Result<ArticleResponse>
}