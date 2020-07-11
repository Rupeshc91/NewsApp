package com.android.newsapp.data

import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val remoteDataSource: NewsRemoteDataSource) :
    NewsRepository {

    override suspend fun getSources() {
        remoteDataSource.getSources()
    }

    override suspend fun getNews(page: Int) {
        remoteDataSource.getNews(page)
    }
}