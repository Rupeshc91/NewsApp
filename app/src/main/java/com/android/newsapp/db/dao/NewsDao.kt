package com.android.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.newsapp.db.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleEntity)

    @Query("SELECT * from article where title=:title")
    fun getNewsFlow(title: String): Flow<List<ArticleEntity>>

    @Query("SELECT * from article where source_name=:source")
    fun getNews(source: String): List<ArticleEntity>
}