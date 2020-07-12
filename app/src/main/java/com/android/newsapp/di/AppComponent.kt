package com.android.newsapp.di

import android.app.Application
import com.android.newsapp.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataModule::class,
    ViewModelModule::class, ActivityModule::class, AndroidInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: NewsApplication)
}