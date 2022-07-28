package com.android.newsapp.data

import com.android.newsapp.api.ApiService
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BaseDataSource() {

    suspend fun getSources() = getResult {
        apiService.getSources()
    }

    suspend fun getNews(page: Int, source: String) =  getResult {
        apiService.getNews(page = page, sources = source)
    }
}