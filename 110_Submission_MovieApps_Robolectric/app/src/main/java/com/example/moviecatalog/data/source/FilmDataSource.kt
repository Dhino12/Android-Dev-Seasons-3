package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.*
import com.example.moviecatalog.data.source.local.entity.tv_entity.*
import com.example.moviecatalog.data.source.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntitys>>>

    fun getAllTv():LiveData<Resource<PagedList<TvEntitys>>>

    fun getMovieDetail(movieID:Int?):LiveData<Resource<MovieWithDetail>>

//    fun getMovieWithID(movieId:Int):LiveData<Resource<MovieWithDetail>>

    fun getTvDetail(tvId:Int): LiveData<Resource<TvWithDetail>>

    fun getMovieSearch(query:String):LiveData<List<MovieSearchEntity>>

    fun getTvSearch(query:String):LiveData<List<TvSearchEntity>>

//    fun getMovieGenre(movieID:Int?):LiveData<Resource<List<GenreMovieDetailEntity>>>

//    fun getTvGenre(tvId: Int?):LiveData<List<GenreTvDetailEntity>>

    fun getUpcomingMovie():LiveData<Resource<List<UpcomingMovieEntity>>>

    fun getUpcomingTv():LiveData<Resource<List<UpcomingTvEntity>>>

    fun getTrailerMovie(movieID: Int):LiveData<Resource<MovieTrailerEntity>>

    fun getTrailerTv(tvId: Int):LiveData<Resource<TvTrailerEntity>>

    fun getFavoriteMovie():LiveData<PagedList<MovieDetailEntity>>

    fun setFavoriteMovie(movie:MovieDetailEntity, state:Boolean)

    fun getFavoriteTV():LiveData<PagedList<TvDetailEntity>>

    fun setFavoriteTV(TV:TvDetailEntity, state:Boolean)
}