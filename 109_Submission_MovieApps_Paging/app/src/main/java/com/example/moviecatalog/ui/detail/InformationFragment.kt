package com.example.moviecatalog.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.GenreDetailAdapter
import com.example.moviecatalog.adapter.GenreDetailTvAdapter
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.vo.Status
import com.example.moviecatalog.viewmodel.DetailMovieViewModel
import com.example.moviecatalog.viewmodel.DetailTVViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.fragment_information.*


class InformationFragment(private val movieDetailEntity: Boolean?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){
            if(movieDetailEntity != null){

                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailMovieViewModel::class.java]

                viewModel.movieDetail.observe(viewLifecycleOwner, Observer {
                    movie ->
                    if(movie != null){
                        when(movie.status){
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                if(movie.data != null) {
                                    implementationView(movie.data.mMovieDetail)
                                    val gridLayoutManager: GridLayoutManager =
                                        object : GridLayoutManager(context, 4) {
                                            override fun canScrollVertically(): Boolean { return false }
                                        }

                                    rvGenre.layoutManager = gridLayoutManager
                                    val gridGenre = GenreDetailAdapter()

                                    val d = movie.data.mMovieDetail.name_genre!!.replace(",","1").filter { it.isDigit() || it.isLowerCase() || it.isUpperCase() }
                                    val p = d.split("1")
                                    gridGenre.setMovie(p)
                                    rvGenre.adapter = gridGenre
                                }

                            }
                            Status.ERROR -> {
                                progressBarDetail.visibility = View.GONE
                                Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })

            }else{

                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailTVViewModel::class.java]

                viewModel.tvDetail.observe(viewLifecycleOwner, Observer {
                        tvWithID ->
                    if(tvWithID.data != null){
                        when(tvWithID.status){
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                implementationView(tvEntity = tvWithID.data.mTvDetail)

                                // ======= Genre ========
                                val d = tvWithID.data.mTvDetail.name_genre!!.replace(",","1").filter { it.isDigit() || it.isLowerCase() || it.isUpperCase() }
                                val p = d.split("1")

                                val gridLayoutManager: GridLayoutManager =
                                    object : GridLayoutManager(context, 4) {
                                        override fun canScrollVertically(): Boolean {
                                            return false
                                        }
                                    }
                                rvGenre.layoutManager = gridLayoutManager
                                val gridGenre = GenreDetailTvAdapter()
                                gridGenre.setData(p)
                                rvGenre.adapter = gridGenre
                                // ========================
                            }
                            Status.ERROR -> {
                                Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun implementationView(
        movieEntityMovie: MovieDetailEntity? = null,
        tvEntity: TvDetailEntity? = null
    ){
        if(movieEntityMovie != null){
            tv_ReleaseDateDetail.text = movieEntityMovie.releaseDate
            tv_synopsis.text = movieEntityMovie.overview
            tv_OriginalName.text = movieEntityMovie.original_title
            tv_popularityValue.text = movieEntityMovie.popularity.toString()
        }else{
            tv_ReleaseDateDetail.text = tvEntity?.firstAirDate
            tv_synopsis.text = tvEntity?.overview
            tv_OriginalName.text = tvEntity?.originalName
            tv_popularityValue.text = tvEntity?.popularity.toString()
        }
    }

}