package com.android.newsapp.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.newsapp.extensions.loadImage
import com.google.gson.annotations.SerializedName

@BindingAdapter("articleImage")
fun loadArticleImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.loadImage(url)
    }
}

data class Article(
    @SerializedName("source") val source: Source,
    @SerializedName("title") val title: String,
    @SerializedName("urlToImage") val imageUrl: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("url")val url:String
)