package com.android.newsapp.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.newsapp.MainActivity
import com.android.newsapp.api.Result
import com.android.newsapp.databinding.FragmentNewsBinding
import com.android.newsapp.di.ViewModelFactory
import com.android.newsapp.di.home.HomeComponent
import com.android.newsapp.model.Article
import com.android.newsapp.viewutils.InfiniteScrollListener
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class NewsFragment : Fragment(), NewsAdapter.Callback {

    private lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentNewsBinding
    private var isLoadMore: Boolean = false
    private var pageNumber: Int = 1
    private lateinit var source: String
    private var isRequesting: Boolean = false
    private var homeComponent: HomeComponent? = null

    private val newsViewModel by viewModels<NewsViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            homeComponent = context.homeComponent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeComponent?.inject(this)
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_SOURCE_ID)?.apply {
            source = this
            newsViewModel.getNews(pageNumber++, source)
        }
        val infiniteScrollListener =
            object : InfiniteScrollListener(binding.newsList.layoutManager as LinearLayoutManager) {
                override fun onLoadMore() {
                    isLoadMore = true
                    if (!isDataLoading()) {
                        newsViewModel.getNews(pageNumber++, source)
                        isRequesting = true
                    }
                }

                override fun isDataLoading(): Boolean {
                    return isRequesting
                }
            }
        newsAdapter = NewsAdapter(this)
        with(binding.newsList) {
            adapter = newsAdapter
            addOnScrollListener(infiniteScrollListener)
        }
        subscribeOnUi()
    }

    private fun subscribeOnUi() {
        newsViewModel.newsLiveData.observe(viewLifecycleOwner, Observer {
            isRequesting = false
            when (it.status) {
                Result.Status.SUCCESS -> {
                    isLoadMore = false
                    binding.progressCircular.visibility = View.GONE
                    var response = it.data
                    response?.articles?.let {
                        newsAdapter.setData(it)
                    }
                }
                Result.Status.LOADING -> {
                    if (!isLoadMore) {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
                Result.Status.ERROR -> {
                    isLoadMore = false
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        private const val ARG_SOURCE_ID = "source_id"

        /**
         * Returns a new instance of this fragment for the given source
         * id.
         */
        @JvmStatic
        fun newInstance(sourceId: String): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SOURCE_ID, sourceId)
                }
            }
        }
    }

    override fun onItemClick(article: Article) {
        activity?.let { WebViewActivity.launchWebViewActivity(it, article.url) }
    }
}