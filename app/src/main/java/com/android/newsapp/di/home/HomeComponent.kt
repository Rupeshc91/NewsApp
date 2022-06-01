package com.android.newsapp.di.home

import com.android.newsapp.MainActivity
import com.android.newsapp.news.NewsFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
@HomeScope
interface HomeComponent {
   fun inject(mainActivity: MainActivity)
   fun inject(fragment: NewsFragment)
}