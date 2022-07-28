package com.android.newsapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.android.newsapp.model.Source
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName


@Entity(tableName = "article")
data class ArticleEntity(
    @ColumnInfo(name = "source")
    @SerializedName("source") val source: Source,
    @ColumnInfo(name = "title")
    @SerializedName("title") val title: String,
    @ColumnInfo(name = "imageUrl")
    @SerializedName("imageUrl") val imageUrl: String,
    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt") val publishedAt: String,
    @PrimaryKey
    @ColumnInfo(name = "url")
    @SerializedName("url") val url: String,
    @ColumnInfo(name = "page")
    @SerializedName("page") val page: Int,
    @ColumnInfo(name = "source_name")
    @SerializedName("source_name") val sourceName: String
)

class TypeConverters {
    @TypeConverter
    fun convertSourceToString(source: Source) = Gson().toJson(source)

    @TypeConverter
    fun convertStringToSource(source: String): Source = Gson().fromJson(source, Source::class.java)
}