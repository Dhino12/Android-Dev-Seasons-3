package com.example.moviecatalog.ui.movie_show

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.MovieHorizontalAdapter
import com.example.moviecatalog.adapter.MovieSearchAdapter
import com.example.moviecatalog.adapter.MoviesAdapter
import com.example.moviecatalog.data.source.vo.Status
import com.example.moviecatalog.viewmodel.MovieViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.fragment_film.*

class MovieFragment : Fragment() {

    private lateinit var moviesAdapter:MoviesAdapter
    lateinit var viewModel:MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){

            val factory = ViewModelFactory.getInstance(requireActivity(), requireContext())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            moviesAdapter = MoviesAdapter()
            getMoviePopular()

            val searchAdapter = MovieSearchAdapter()
            progressBar.visibility = View.VISIBLE
            search_bars.queryHint = getString(R.string.searchMovie)
            search_bars.setOnSearchClickListener {
                applicationName.visibility = View.GONE
            }
            search_bars.setOnCloseListener {
                applicationName.visibility = View.VISIBLE
                search_bars.queryHint = ""
                rilis.text = resources.getString(R.string.watchNow)
                getMoviePopular()
                false
            }
            search_bars.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query != null){
                        progressBar.visibility = View.VISIBLE
                        viewModel.getSearchMovie(query.trim()).observe(viewLifecycleOwner,{
                            search_movie ->
                                if(search_movie != null){
                                    rilis.text = getString(R.string.resultSearch)
                                    searchAdapter.setSearch(search_movie)
                                    progressBar.visibility = View.GONE
                                    searchAdapter.notifyDataSetChanged()

                                    with(rvFilms){
                                        layoutManager = GridLayoutManager(context,3)
                                        setHasFixedSize(true)
                                        adapter = searchAdapter
                                    }
                                }
                        })
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.equals("")) {

                    }
                    return false
                }

            })

            val movieHorizontalAdapter = MovieHorizontalAdapter()
            viewModel.getUpMovies().observe(viewLifecycleOwner, {
                upMovies ->
                if(upMovies != null){
                    when(upMovies.status){
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            if(upMovies.data != null){
                                progressBar.visibility = View.VISIBLE
                                movieHorizontalAdapter.setMovie(upMovies.data)
                                progressBar.visibility = View.GONE
                                movieHorizontalAdapter.notifyDataSetChanged()

                                with(rvHorFilms){
                                    layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
                                    setHasFixedSize(true)
                                    adapter = movieHorizontalAdapter
                                }
                            }
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.VISIBLE
                            Toast.makeText(context, "Terjadi Kesalahan",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }

    private fun getMoviePopular(){
        viewModel.getMovies().observe(viewLifecycleOwner, {
                moviePop ->
            if(moviePop != null){
                when(moviePop.status){
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        moviesAdapter.submitList(moviePop.data)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.VISIBLE
                        Toast.makeText(context, "Terjadi Kesalahan",Toast.LENGTH_LONG).show()
                    }
                }
            }
            with(rvFilms){
                layoutManager = GridLayoutManager(context,3)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        })

    }
}