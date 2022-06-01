package com.android.newsapp.di.login

import com.android.newsapp.data.LoginDataSource
import com.android.newsapp.data.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @LoginScope
    fun providesLoginRepository(loginDataSource: LoginDataSource) = LoginRepository(loginDataSource)
}