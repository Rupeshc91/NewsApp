package com.android.newsapp.data

import com.android.newsapp.api.Result
import com.android.newsapp.model.ArticleResponse
import com.android.newsapp.model.SourceResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(val remoteDataSource: NewsRemoteDataSource) :
    NewsRepository {

    override suspend fun getSources(): Result<SourceResponse> {
        return remoteDataSource.getSources()
    }

    override suspend fun getNews(page: Int,source:String): Result<ArticleResponse> {
        return remoteDataSource.getNews(page,source)
    }
}