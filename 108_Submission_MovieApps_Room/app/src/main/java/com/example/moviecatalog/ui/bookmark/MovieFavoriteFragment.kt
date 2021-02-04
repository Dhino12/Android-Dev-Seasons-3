package com.example.moviecatalog.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.FavoriteMovieAdapter
import com.example.moviecatalog.viewmodel.FavoriteMovieViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*

class MovieFavoriteFragment : Fragment() {

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
            val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

            val adapters = FavoriteMovieAdapter(this)
            progressBarFavorite.visibility = View.VISIBLE
            viewModel.getMovieFavorite().observe(viewLifecycleOwner, Observer {
                movies ->
                    progressBarFavorite.visibility = View.GONE
                    adapters.setMovie(movies)
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