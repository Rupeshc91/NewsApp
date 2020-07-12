package com.android.newsapp.extensions

import android.widget.ImageView
import com.android.newsapp.GlideApp
import com.android.newsapp.R

fun ImageView.loadImage(url: String, placeholder: Int = R.drawable.ic_launcher_background) {
    GlideApp.with(context).load(url).placeholder(placeholder).into(this)
}

