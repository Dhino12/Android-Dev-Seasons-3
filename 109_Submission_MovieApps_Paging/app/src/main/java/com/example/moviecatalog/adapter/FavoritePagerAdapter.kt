package com.example.moviecatalog.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.bookmark.MovieFavoriteFragment
import com.example.moviecatalog.ui.bookmark.TvFavoriteFragment
import com.example.moviecatalog.ui.detail.InformationFragment
import com.example.moviecatalog.ui.detail.TrailerFragment
import com.example.moviecatalog.ui.movie_show.MovieFragment
import com.example.moviecatalog.ui.tv_show.TvShowFragment

class FavoritePagerAdapter(private val mContext:Context, fm:FragmentManager):FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private var TAB_TITLE = intArrayOf(R.string.movieTAB, R.string.tvTAB)
    }

    var movieON:Boolean? = null

    override fun getCount(): Int = TAB_TITLE.size

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MovieFavoriteFragment()
            1 -> TvFavoriteFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLE[position])

}