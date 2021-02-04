package com.example.myacademy.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myacademy.R
import com.example.myacademy.view.academy.AcademyFragment
import com.example.myacademy.view.bookmark.BookmarkFragment

class SectionsPageAdapter(private val mContext:Context, fm :FragmentManager):FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.home, R.string.bookmark)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AcademyFragment()
            1 -> BookmarkFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }
}