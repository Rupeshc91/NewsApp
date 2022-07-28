package com.android.newsapp.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.android.newsapp.extensions.loadImage

@BindingAdapter("articleImage")
fun loadArticleImage(imageView: ImageView, url: String?) {
    url?.let {
        imageView.loadImage(url)
    }
}