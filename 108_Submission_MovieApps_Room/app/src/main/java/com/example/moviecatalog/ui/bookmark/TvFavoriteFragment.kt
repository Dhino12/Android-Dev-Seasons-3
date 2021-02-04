package com.example.moviecatalog.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.FavoriteMovieAdapter
import com.example.moviecatalog.adapter.FavoriteTvAdapter
import com.example.moviecatalog.viewmodel.FavoriteMovieViewModel
import com.example.moviecatalog.viewmodel.FavoriteTvViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*


class TvFavoriteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity(), requireContext())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvViewModel::class.java]

            val adapters = FavoriteTvAdapter()
            progressBarFavorite.visibility = View.VISIBLE
            viewModel.getMovieFavorite().observe(viewLifecycleOwner, Observer {
                    tv ->
                progressBarFavorite.visibility = View.GONE
                adapters.setTv(tv)
                adapters.notifyDataSetChanged()
            })

            with(rvFilmsFavorite){
                layoutManager = GridLayoutManager(context,3)
                setHasFixedSize(true)
                adapter = adapters
            }
        }
    }
}