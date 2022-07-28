package com.android.newsapp.data

import com.android.newsapp.api.Result
import com.android.newsapp.model.Article
import com.android.newsapp.model.SourceResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getSources(): Result<SourceResponse>

     fun getNews(page: Int, source: String): Flow<Result<List<Article>>>
}