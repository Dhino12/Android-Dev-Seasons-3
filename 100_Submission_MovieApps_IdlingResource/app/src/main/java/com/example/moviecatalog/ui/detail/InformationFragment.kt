package com.example.moviecatalog.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.adapter.GenreDetailAdapter
import com.example.moviecatalog.adapter.GenreDetailTvAdapter
import com.example.moviecatalog.data.source.local.MovieDetailEntity
import com.example.moviecatalog.data.source.local.TvDetailEntity
import com.example.moviecatalog.viewmodel.DetailMovieViewModel
import com.example.moviecatalog.viewmodel.DetailTVViewModel
import com.example.moviecatalog.viewmodel.ViewModelFactory
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

                viewModel.getDetail().observe(viewLifecycleOwner, { infoMovie ->
                    implementationView(movieEntityMovie = infoMovie)
                })

                viewModel.getGenreDetail().observe(viewLifecycleOwner, { genre ->

                    val gridLayoutManager: GridLayoutManager =
                        object : GridLayoutManager(context, 4) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }
                    rvGenre.layoutManager = gridLayoutManager
                    val gridGenre = GenreDetailAdapter()
                    gridGenre.setMovie(genre)
                    rvGenre.adapter = gridGenre
                })

                viewModel.getTrailer().observe(viewLifecycleOwner, {
                    trailer ->

                })
            }else{

                val factory = ViewModelFactory.getInstance(requireActivity(), requireActivity())
                val viewModel = ViewModelProvider(requireActivity(), factory)[DetailTVViewModel::class.java]

                viewModel.getTvDetail().observe(viewLifecycleOwner, { infoTv ->
                    implementationView(tvEntity = infoTv)
                })

                viewModel.getTvGenre().observe(viewLifecycleOwner, {
                    genreTV ->

                    val gridLayoutManager: GridLayoutManager =
                        object : GridLayoutManager(context, 4) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }

                    rvGenre.layoutManager = gridLayoutManager
                    val gridGenre = GenreDetailTvAdapter()
                    gridGenre.setData(genreTV)
                    rvGenre.adapter = gridGenre
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