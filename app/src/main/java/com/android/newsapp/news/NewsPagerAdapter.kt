package com.android.newsapp.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.newsapp.model.Source

/**
 * A [FragmentStatePagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class NewsPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var sources = mutableListOf<Source>()

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a NewsFragment (defined as a static inner class below).
        return NewsFragment.newInstance(sources[position].id)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return sources[position].name
    }

    override fun getCount(): Int {
        return sources.size
    }

    public fun setSource(sources: List<Source>) {
        sources.let {
            this.sources.clear()
            this.sources.addAll(sources)
            notifyDataSetChanged()
        }
    }
}