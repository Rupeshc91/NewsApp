package com.android.newsapp.data

interface NewsRepository {

    suspend fun getSources()

    suspend fun getNews(page: Int)
}