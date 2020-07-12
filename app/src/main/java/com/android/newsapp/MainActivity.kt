package com.android.newsapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.newsapp.api.Result
import com.android.newsapp.databinding.ActivityMainBinding
import com.android.newsapp.di.ViewModelFactory
import com.android.newsapp.news.NewsPagerAdapter
import com.android.newsapp.news.NewsViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val newsViewModel by viewModels<NewsViewModel> {
        viewModelFactory
    }

    private val sectionsPagerAdapter = NewsPagerAdapter(
        supportFragmentManager
    )
    private lateinit var binding: ActivityMainBinding

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        newsViewModel.getSource()
        subscribeOnUi()

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun subscribeOnUi() {
        newsViewModel.sourceLiveData.observe(this, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressCircular.visibility = View.GONE
                    it?.data?.sources?.let { it1 ->
                        sectionsPagerAdapter.setSource(
                            it1.subList(
                                0,
                                10
                            )
                        )
                    }
                }
                Result.Status.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}