package com.android.newsapp.di

import com.android.newsapp.NewsApplication

object AppInjector {

    lateinit var appComponent: AppComponent

    fun injectAppComponent(application: NewsApplication) {
        synchronized(AppInjector::class) {
            appComponent = DaggerAppComponent.builder()
                .build()
            appComponent.inject(application)
        }
    }
}