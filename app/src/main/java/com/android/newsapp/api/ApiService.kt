package com.android.newsapp.api

import retrofit2.http.GET

interface ApiService {

    @GET(PATH_SOURCES_V2)
    suspend fun getSources()
}