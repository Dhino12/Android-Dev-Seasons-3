package com.example.moviecatalog.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalog.R
import com.example.moviecatalog.ui.detail.InformationFragment
import com.example.moviecatalog.ui.detail.TrailerFragment

class SectionPagerAdapter(private val mContext:Context, fm:FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.informationTAB, R.string.Trailer)

    }

    var movieON:Boolean? = null

    override fun getCount(): Int = TAB_TITLE.size

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> InformationFragment(movieON)
            1 -> TrailerFragment(movieON)
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLE[position])

}