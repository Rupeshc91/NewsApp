package com.android.newsapp.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.newsapp.databinding.NewsItemViewBinding
import com.android.newsapp.model.Article

class NewsAdapter(val callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var news = mutableListOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            NewsItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), callback
        )
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun setData(news: List<Article>) {
        news.let {
            this.news.addAll(news)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsViewHolder) {
            holder.updateView(news[position])
        }
    }

    class NewsViewHolder(val newsItemViewBinding: NewsItemViewBinding, val callback: Callback) :
        RecyclerView.ViewHolder(newsItemViewBinding.root) {
        private lateinit var article: Article

        init {
            newsItemViewBinding.root.setOnClickListener(View.OnClickListener {
                callback.onItemClick(article)
            })
        }

        fun updateView(article: Article) {
            this.article = article
            newsItemViewBinding.news = article
            newsItemViewBinding.executePendingBindings()
        }
    }

    interface Callback {
        fun onItemClick(article: Article)
    }
}