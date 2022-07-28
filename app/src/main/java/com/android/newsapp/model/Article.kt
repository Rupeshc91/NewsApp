package com.android.newsapp.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source") val source: Source,
    @SerializedName("title") val title: String,
    @SerializedName("urlToImage") val imageUrl: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("url")val url:String
)