package com.example.moviecatalog.data.source.local

//import com.example.moviecatalog.data.source.local.entity.CombinedMovieEntity.MovieWithDetail
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvTrailerEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDAO:FilmDao){
    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao:FilmDao):LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    // ================ Movie ================

    fun getAllMovie():LiveData<List<MovieEntitys>> =
        mFilmDAO.getMovie()

    fun insertMovie(movie:List<MovieEntitys>) =
        mFilmDAO.insertMovie(movie)

    fun getDetailMovieWithID(movieId:Int?):LiveData<MovieWithDetail> =
        mFilmDAO.getDetailWithMovieById(movieId)

    fun insertMovieDetail(movieDetail: MovieDetailEntity) =
        mFilmDAO.insertMovieDetail(movieDetail)

    fun getTrailerMovie(movieId:Int):LiveData<MovieTrailerEntity> =
        mFilmDAO.getTrailerMovie(movieId)

    fun insertTrailerMovie(trailer: MovieTrailerEntity) =
        mFilmDAO.insertTrailerMovie(trailer)

    fun getUpcomingMovie():LiveData<List<UpcomingMovieEntity>> =
        mFilmDAO.getUpcomingMovie()

    fun insertUpcomingMovie(upComingMovie:List<UpcomingMovieEntity>) =
        mFilmDAO.insertUpcomingMovie(upComingMovie)

    // ================== TV ==================

    fun getAllTv():LiveData<List<TvEntitys>> =
        mFilmDAO.getTv()

    fun insertTv(tv:List<TvEntitys>) =
        mFilmDAO.insertTv(tv)

    fun getDetailTvWithID(tvId:Int):LiveData<TvWithDetail> =
        mFilmDAO.getDetailWithTv(tvId)

    fun insertTvDetail(tvDetail:TvDetailEntity) =
        mFilmDAO.insertTvDetail(tvDetail)

    fun getTrailerTv(tvId:Int):LiveData<TvTrailerEntity> =
        mFilmDAO.getTvTrailer(tvId)

    fun insertTrailerTv(trailer:TvTrailerEntity) =
        mFilmDAO.insertTrailerTv(trailer)

    fun getUpComingTv():LiveData<List<UpcomingTvEntity>> =
        mFilmDAO.getUpComingTv()

    fun insertUpComingTv(upComingTv:List<UpcomingTvEntity>) =
        mFilmDAO.insertUpComingTv(upComingTv)

    // ================ Favorite ================

    fun getFavoriteMovie():LiveData<List<MovieDetailEntity>> =
        mFilmDAO.getFavoriteMovie()

    fun insertFavoriteMovie(movie:MovieDetailEntity, newState:Boolean){
        movie.favorite = newState
        mFilmDAO.updateMovie(movie)
    }

    fun getFavoriteTV():LiveData<List<TvDetailEntity>> =
        mFilmDAO.getFavoriteTV()

    fun insertFavoriteTV(tv:TvDetailEntity, newState: Boolean){
        tv.favorit = newState
        mFilmDAO.updateTV(tv)
    }
}