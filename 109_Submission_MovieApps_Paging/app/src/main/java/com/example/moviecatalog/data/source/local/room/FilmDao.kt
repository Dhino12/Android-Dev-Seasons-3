package com.example.moviecatalog.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
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

@Dao
interface FilmDao {

    // ================ Movie ================
    @Query("SELECT * FROM MovieEntitys")
    fun getMovie(): DataSource.Factory<Int, MovieEntitys>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie:List<MovieEntitys>)

    @Transaction
    @Query("SELECT * FROM MovieDetailEntity WHERE id = :movieId")
    fun getDetailWithMovieById(movieId:Int?): LiveData<MovieWithDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: MovieDetailEntity)

    @Query("SELECT * FROM movieTrailer WHERE id = :movieId")
    fun getTrailerMovie(movieId: Int?):LiveData<MovieTrailerEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertTrailerMovie(trailer: MovieTrailerEntity)

    @Query("SELECT * FROM upcomingMovie")
    fun getUpcomingMovie():LiveData<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovie(upcomingMovie:List<UpcomingMovieEntity>)

    // ================ Tv ================
    @Query("SELECT * FROM tventity")
    fun getTv():DataSource.Factory<Int, TvEntitys>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv:List<TvEntitys>)

    @Transaction
    @Query("SELECT * FROM tvDetailEntity WHERE id = :tvID")
    fun getDetailWithTv(tvID:Int):LiveData<TvWithDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvDetail(tv:TvDetailEntity)

    @Query("SELECT * FROM tvTrailer WHERE id = :tvID")
    fun getTvTrailer(tvID: Int):LiveData<TvTrailerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrailerTv(trailer:TvTrailerEntity)

    @Query("SELECT * FROM upComingTv")
    fun getUpComingTv():LiveData<List<UpcomingTvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpComingTv(upcomingTv: List<UpcomingTvEntity>)

    // ================ Favorite ================
    @Query("SELECT * FROM moviedetailentity WHERE favorite = 1")
    fun getFavoriteMovie():DataSource.Factory<Int, MovieDetailEntity>

    @Update
    fun updateMovie(movie: MovieDetailEntity)

    @Query("SELECT * FROM tvDetailEntity WHERE favorit = 1")
    fun getFavoriteTV():DataSource.Factory<Int, TvDetailEntity>

    @Update
    fun updateTV(movie: TvDetailEntity)
}