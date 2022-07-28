package com.android.newsapp.di.module

import com.android.newsapp.api.ApiService
import com.android.newsapp.data.NewsLocalDataSource
import com.android.newsapp.data.NewsRemoteDataSource
import com.android.newsapp.data.NewsRepository
import com.android.newsapp.data.NewsRepositoryImpl
import com.android.newsapp.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository =
        NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)


    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(apiService: ApiService) = NewsRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providesNewsLocalDataSource(newsDao: NewsDao) = NewsLocalDataSource(newsDao)

}