package com.android.newsapp.data

import com.android.newsapp.db.dao.NewsDao
import com.android.newsapp.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    suspend fun save(articleEntity: ArticleEntity) {
        newsDao.insert(articleEntity)
    }

    suspend fun getNewsFlow(source: String): Flow<List<ArticleEntity>> {
        return newsDao.getNewsFlow(source)
    }

    fun getNews(source: String, page: Int): List<ArticleEntity> {
        return newsDao.getNews(source)
    }
}