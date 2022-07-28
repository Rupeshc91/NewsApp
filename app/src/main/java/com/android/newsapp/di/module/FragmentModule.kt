package com.android.newsapp.di.module

import com.android.newsapp.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment
}