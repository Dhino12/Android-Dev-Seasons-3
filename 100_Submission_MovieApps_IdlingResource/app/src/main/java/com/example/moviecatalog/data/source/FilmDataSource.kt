package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.*

interface FilmDataSource {

    fun getAllMovies(): LiveData<List<MovieEntitys>>

    fun getAllTv():LiveData<List<TvEntitys>>

    fun getMovieDetail(movieID:Int?):LiveData<MovieDetailEntity>

    fun getTvDetail(tvId:Int?): LiveData<TvDetailEntity>

    fun getMovieGenre(movieID:Int?):LiveData<List<GenreMovieDetailEntity>>

    fun getTvGenre(tvId: Int?):LiveData<List<GenreTvDetailEntity>>

    fun getUpcomingMovie():LiveData<List<UpcomingMovieEntity>>

    fun getUpcomingTv():LiveData<List<UpcomingTvEntity>>

    fun getTrailerMovie(movieID: Int?):LiveData<List<MovieTrailerEntity>>

    fun getTrailerTv(tvId: Int?):LiveData<List<TvTrailerEntity>>
}