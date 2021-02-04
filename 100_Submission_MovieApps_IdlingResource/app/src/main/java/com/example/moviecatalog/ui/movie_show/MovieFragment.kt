package com.example.moviecatalog.ui.movie_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.MovieHorizontalAdapter
import com.example.moviecatalog.adapter.MoviesAdapter
import com.example.moviecatalog.viewmodel.MovieViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_film.*

class MovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){

            val factory = ViewModelFactory.getInstance(requireActivity(), requireContext())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val moviesAdapter = MoviesAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, {
                moviePop ->
                moviesAdapter.setMovies(moviePop)
                progressBar.visibility = View.GONE
                moviesAdapter.notifyDataSetChanged()

                with(rvFilms){
                    layoutManager = GridLayoutManager(context,3)
                    setHasFixedSize(true)
                    adapter = moviesAdapter
                }
            })

            val movieHorizontalAdapter = MovieHorizontalAdapter()
            viewModel.getUpMovies().observe(viewLifecycleOwner, {
                upMovies ->
                movieHorizontalAdapter.setMovie(upMovies)
                progressBar.visibility = View.GONE
                movieHorizontalAdapter.notifyDataSetChanged()

                with(rvHorFilms){
                    layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = movieHorizontalAdapter
                }
            })
        }
    }
}