package com.android.newsapp.di

import com.android.newsapp.api.ApiService
import com.android.newsapp.data.NewsRemoteDataSource
import com.android.newsapp.data.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesNewsRepository(newsRemoteDataSource: NewsRemoteDataSource) =
        NewsRepositoryImpl(newsRemoteDataSource)


    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(apiService: ApiService) = NewsRemoteDataSource(apiService)

}