package com.android.newsapp.data

import android.util.Log
import com.android.newsapp.api.Result
import com.android.newsapp.db.entity.ArticleEntity
import com.android.newsapp.model.Article
import com.android.newsapp.model.SourceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    private var lastFetchedTime = System.currentTimeMillis()

    override suspend fun getSources(): Result<SourceResponse> {
        return newsRemoteDataSource.getSources()
    }

    override fun getNews(page: Int, source: String) = flow {
        // Let's first check in cache. If cache valid than return the cache otherwise call api and
        // store response in cache and return from cache
        val newsLocalList = newsLocalDataSource.getNews(source, page)
        if (newsLocalList.isNullOrEmpty() ||
            System.currentTimeMillis() - lastFetchedTime > CACHE_EXPIRY_TIME
        ) {
            emit(Result.loading())
            val newsResponse = newsRemoteDataSource.getNews(page, source)
            if (newsResponse.status == Result.Status.SUCCESS) {
                newsResponse.data?.articles?.forEach {
                    Log.e("Rupesh","Inserting")
                    newsLocalDataSource.save(
                        ArticleEntity(
                            it.source,
                            it.title,
                            it.imageUrl,
                            it.publishedAt,
                            it.url,
                            page,
                            source
                        )
                    )
                }
            } else if (newsResponse.status == Result.Status.ERROR) {
                emit(Result.error(newsResponse.message ?: ""))
            }
        } else {
            emit(Result.loading())
            val newsResponse = newsRemoteDataSource.getNews(page, source)
            if (newsResponse.status == Result.Status.SUCCESS) {
                newsResponse.data?.articles?.forEach {
                    newsLocalDataSource.save(
                        ArticleEntity(
                            it.source,
                            it.title,
                            it.imageUrl,
                            it.publishedAt,
                            it.url,
                            page,
                            source
                        )
                    )
                }
            } else if (newsResponse.status == Result.Status.ERROR) {
                emit(Result.error(newsResponse.message ?: ""))
            }
        }
        Log.e("Rupesh","newsList get called $source $page")

        val newsList = newsLocalDataSource.getNews(source, page).map {
            Article(it.source, it.title, it.imageUrl, it.publishedAt, it.url)
        }
        Log.e("Rupesh","newsList after get called")

        emit(Result.success(newsList))
    }.flowOn(Dispatchers.IO)

    companion object {
        const val CACHE_EXPIRY_TIME = 15 * 60 * 1000
    }
}