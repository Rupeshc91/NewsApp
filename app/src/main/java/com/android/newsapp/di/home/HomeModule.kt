package com.android.newsapp.di.home

import com.android.newsapp.api.ApiService
import com.android.newsapp.data.NewsRemoteDataSource
import com.android.newsapp.data.NewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule {

    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(apiService: ApiService) = NewsRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository =
        NewsRepository(newsRemoteDataSource)

}