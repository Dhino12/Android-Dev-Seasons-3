package com.example.moviecatalog.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.local.LocalDataSource
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.*
import com.example.moviecatalog.data.source.remote.ApiResponse
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.data.source.remote.response.*
import com.example.moviecatalog.data.source.vo.Resource
import com.example.moviecatalog.utils.AppExecutors
import kotlin.collections.ArrayList

class FilmRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors:AppExecutors
):FilmDataSource{

    companion object{
        @Volatile
        private var filmInstance:FilmRepository? = null

        fun getInstanceFilm(
            remoteData:RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): FilmRepository {
            return filmInstance ?: synchronized(this){
                filmInstance ?: FilmRepository(remoteData,localDataSource, appExecutors)
            }
        }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntitys>>> {
        return object : NetworkBoundResource<PagedList<MovieEntitys>, List<ResultsItemMovie>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntitys>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovie(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntitys>?): Boolean {
                Log.e("errorDataShould",data?.isEmpty().toString())
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemMovie>>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: List<ResultsItemMovie>) {
                val movieList = ArrayList<MovieEntitys>()
                for (response in data){
                    val movie = MovieEntitys(
                        response.releaseDate,
                        response.popularity,
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTv(): LiveData<Resource<PagedList<TvEntitys>>> {
        return object : NetworkBoundResource<PagedList<TvEntitys>, List<ResultsItemTV>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvEntitys>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntitys>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemTV>>> =
                remoteDataSource.getTv()

            override fun saveCallResult(data: List<ResultsItemTV>) {
                val tvPopList = ArrayList<TvEntitys>()

                for (response in data){
                    val tvPop = TvEntitys(
                        response.firstAirDate,
                        response.overview,
                        response.popularity,
                        response.name,
                        response.id,
                        response.posterPath,
                    )
                    tvPopList.add(tvPop)
                }
                localDataSource.insertTv(tvPopList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieID: Int?): LiveData<Resource<MovieWithDetail>> {

        return object : NetworkBoundResource<MovieWithDetail, MovieDetailResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieWithDetail> {
                return localDataSource.getDetailMovieWithID(movieID)
            }

            override fun shouldFetch(data: MovieWithDetail?): Boolean {
                Log.e("errorDataShouldDetail",(data == null).toString())
                return data?.mMovieDetail == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> {
                return remoteDataSource.getMovieDetail(movieID)
            }

            override fun saveCallResult(data: MovieDetailResponse) {

                val listGenre = ArrayList<String?>()
                for (datas in data.genre!!){
                    listGenre.add(datas.name)
                }

                val movieDetail = MovieDetailEntity(
                    data.overview,
                    data.title,
                    data.posterPath,
                    data.releaseDate,
                    data.popularity,
                    data.id,
                    data.original_title,
                    listGenre.toString(),
                    false
                )
                Log.e("errorDataSAVEDetail",movieDetail.toString())
                localDataSource.insertMovieDetail(movieDetail)
            }
        }.asLiveData()
    }

    override fun getTvDetail(tvId: Int): LiveData<Resource<TvWithDetail>> {
        return object : NetworkBoundResource<TvWithDetail, TvDetailResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvWithDetail> =
                localDataSource.getDetailTvWithID(tvId)

            override fun shouldFetch(data: TvWithDetail?): Boolean =
                data?.mTvDetail == null

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> =
                remoteDataSource.getTvDetail(tvId)

            override fun saveCallResult(data: TvDetailResponse) {
                val listGenre = ArrayList<String?>()

                for (genre in data.genre!!){
                    listGenre.add(genre.name)
                }

                val tvDetail = TvDetailEntity(
                    data.firstAirDate,
                    data.overview,
                    data.posterPath,
                    data.originalName,
                    data.popularity,
                    data.id,
                    listGenre.toString()
                )

                localDataSource.insertTvDetail(tvDetail)
            }
        }.asLiveData()
    }

    override fun getUpcomingMovie(): LiveData<Resource<List<UpcomingMovieEntity>>> {
        return object : NetworkBoundResource<List<UpcomingMovieEntity>, List<ResultsItemUpMovie>>(appExecutors){
            override fun loadFromDB(): LiveData<List<UpcomingMovieEntity>> =
                localDataSource.getUpcomingMovie()

            override fun shouldFetch(data: List<UpcomingMovieEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemUpMovie>>> =
                remoteDataSource.getUpcomingMovie()

            override fun saveCallResult(data: List<ResultsItemUpMovie>) {
                val listUpcomingMovie = ArrayList<UpcomingMovieEntity>()
                for (response in data){
                    val upcoming = UpcomingMovieEntity(
                        response.popularity,
                        response.voteAverage,
                        response.id,
                        response.title,
                        response.posterPath
                    )
                    listUpcomingMovie.add(upcoming)
                }
                Log.e("errorUpcoming",data.toString())
                localDataSource.insertUpcomingMovie(listUpcomingMovie)
            }
        }.asLiveData()
    }

    override fun getUpcomingTv(): LiveData<Resource<List<UpcomingTvEntity>>> {
        return object : NetworkBoundResource<List<UpcomingTvEntity>, List<ResultsItemUpTv>>(appExecutors){
            override fun loadFromDB(): LiveData<List<UpcomingTvEntity>> =
                localDataSource.getUpComingTv()

            override fun shouldFetch(data: List<UpcomingTvEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultsItemUpTv>>> =
                remoteDataSource.getUpcomingTv()

            override fun saveCallResult(data: List<ResultsItemUpTv>) {
                val comingsTv = ArrayList<UpcomingTvEntity>()
                for (response in data){
                    val upTv = UpcomingTvEntity(
                        response.popularity,
                        response.voteAverage,
                        response.name,
                        response.id,
                        response.posterPath
                    )
                    comingsTv.add(upTv)
                }
                localDataSource.insertUpComingTv(comingsTv)
            }
        }.asLiveData()
    }

    override fun getTrailerMovie(movieID: Int): LiveData<Resource<MovieTrailerEntity>> {
        return object : NetworkBoundResource<MovieTrailerEntity, List<ResultsTrailerMovie>>(appExecutors){
            override fun loadFromDB(): LiveData<MovieTrailerEntity> =
                localDataSource.getTrailerMovie(movieID)

            override fun shouldFetch(data: MovieTrailerEntity?): Boolean {
                Log.e("errorTrailer",(data == null).toString())
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<List<ResultsTrailerMovie>>> =
                remoteDataSource.getTrailerMovie(movieID)


            override fun saveCallResult(data: List<ResultsTrailerMovie>) {
                val nameTrailer = ArrayList<String>()
                val keyTrailer = ArrayList<String>()
                for(i in data){
                    if(i.name != null && i.key != null){
                        nameTrailer.add(i.name.replace(",","*"))
                        keyTrailer.add(i.key)
                    }
                }
                val trailers = MovieTrailerEntity(
                    movieID,
                    name = nameTrailer.toString(),
                    key = keyTrailer.toString()
                )
                Log.e("errorTrailerSave",data.toString())
                localDataSource.insertTrailerMovie(trailers)
            }
        }.asLiveData()
    }

    override fun getTrailerTv(tvId: Int): LiveData<Resource<TvTrailerEntity>> {

        return object : NetworkBoundResource<TvTrailerEntity, List<ResultsTrailerTv>>(appExecutors){
            override fun loadFromDB(): LiveData<TvTrailerEntity> =
                localDataSource.getTrailerTv(tvId)

            override fun shouldFetch(data: TvTrailerEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<ResultsTrailerTv>>> =
                remoteDataSource.getTrailerTv(tvId)

            override fun saveCallResult(data: List<ResultsTrailerTv>) {
                val nameTrailer = ArrayList<String>()
                val keyTrailer = ArrayList<String>()
                for(i in data){
                    if(i.name != null && i.key != null){
                        nameTrailer.add(i.name.replace(",","*"))
                        keyTrailer.add(i.key)
                    }
                }
                val trailers = TvTrailerEntity(
                    tvId,
                    nameTrailer.toString(),
                    keyTrailer.toString()
                )
                localDataSource.insertTrailerTv(trailers)
            }
        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.insertFavoriteMovie(movie, state) }
    }

    override fun getFavoriteTV(): LiveData<PagedList<TvDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTV(), config).build()
    }

    override fun setFavoriteTV(TV: TvDetailEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.insertFavoriteTV(TV,state) }
    }

}