package com.android.newsapp.di.module

import android.app.Application
import com.android.newsapp.db.AppDatabase
import com.android.newsapp.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
        AppDatabase.getInstance(application.applicationContext)

    @Provides
    fun providesNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.getNewsDao()
}