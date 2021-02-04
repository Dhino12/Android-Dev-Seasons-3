package com.example.moviecatalog.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.favorite.FavoritePagerAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*

class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val favoritePagerAdapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        view_pager.adapter = favoritePagerAdapter
        tabsBookmark.setupWithViewPager(view_pager)
    }
}