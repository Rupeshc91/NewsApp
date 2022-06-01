package com.android.newsapp

import android.app.Application
import com.android.newsapp.di.AppInjector

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.injectAppComponent(this)
    }
}