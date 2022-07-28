package com.android.newsapp.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.newsapp.api.Result
import com.android.newsapp.data.NewsRepositoryImpl
import com.android.newsapp.model.SourceResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsViewModel @Inject constructor(private val newsRepository: NewsRepositoryImpl) : ViewModel() {

    val sourceLiveData = MutableLiveData<Result<SourceResponse>>()

    fun getSource() {
        viewModelScope.launch {
            sourceLiveData.postValue(Result.loading(null))
            val response = newsRepository.getSources()
            sourceLiveData.postValue(response)
        }
    }

    fun getNews(pageNumber: Int, source: String) = newsRepository.getNews(pageNumber, source)

}