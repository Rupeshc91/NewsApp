package com.android.newsapp.di

import com.android.newsapp.NewsApplication
import com.android.newsapp.di.home.HomeComponent
import com.android.newsapp.di.home.HomeModule
import com.android.newsapp.di.login.LoginComponent
import com.android.newsapp.di.login.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class,
        ViewModelModule::class,  AppModule::class]
)
interface AppComponent {

    fun inject(application: NewsApplication)
    fun plusLoginComponent(loginModule: LoginModule): LoginComponent
    fun plusHomeComponent(homeModule: HomeModule): HomeComponent
}