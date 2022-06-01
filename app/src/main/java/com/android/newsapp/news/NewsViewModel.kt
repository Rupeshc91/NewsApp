package com.android.newsapp.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.api.Result
import com.android.newsapp.data.NewsRepository
import com.android.newsapp.model.ArticleResponse
import com.android.newsapp.model.SourceResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val sourceLiveData = MutableLiveData<Result<SourceResponse>>()
    val newsLiveData = MutableLiveData<Result<ArticleResponse>>()

    fun getSource() {
        viewModelScope.launch {
            sourceLiveData.postValue(Result.loading(null))
            val response = newsRepository.getSources()
            sourceLiveData.postValue(response)
        }
    }

    fun getNews(pageNumber: Int, source: String) {
        viewModelScope.launch {
            newsLiveData.postValue(Result.loading(null))
            val response = newsRepository.getNews(pageNumber, source)
            newsLiveData.postValue(response)
        }
    }

    fun refreshNews(source: String) {
        viewModelScope.launch {
            val response = newsRepository.getNews(1, source)
            newsLiveData.postValue(response)
        }
    }
}