package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.*
import com.example.moviecatalog.data.source.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntitys>>>

    fun getAllTv():LiveData<Resource<List<TvEntitys>>>

    fun getMovieDetail(movieID:Int?):LiveData<Resource<MovieWithDetail>>

//    fun getMovieWithID(movieId:Int):LiveData<Resource<MovieWithDetail>>

    fun getTvDetail(tvId:Int): LiveData<Resource<TvWithDetail>>

//    fun getMovieGenre(movieID:Int?):LiveData<Resource<List<GenreMovieDetailEntity>>>

//    fun getTvGenre(tvId: Int?):LiveData<List<GenreTvDetailEntity>>

    fun getUpcomingMovie():LiveData<Resource<List<UpcomingMovieEntity>>>

    fun getUpcomingTv():LiveData<Resource<List<UpcomingTvEntity>>>

    fun getTrailerMovie(movieID: Int):LiveData<Resource<MovieTrailerEntity>>

    fun getTrailerTv(tvId: Int):LiveData<Resource<TvTrailerEntity>>

    fun getFavoriteMovie():LiveData<List<MovieDetailEntity>>

    fun setFavoriteMovie(movie:MovieDetailEntity, state:Boolean)

    fun getFavoriteTV():LiveData<List<TvDetailEntity>>

    fun setFavoriteTV(TV:TvDetailEntity, state:Boolean)
}