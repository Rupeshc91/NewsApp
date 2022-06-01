package com.android.newsapp.di.login

import com.android.newsapp.ui.login.LoginActivity
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
@LoginScope
interface LoginComponent{
  fun inject(loginActivity: LoginActivity)
}