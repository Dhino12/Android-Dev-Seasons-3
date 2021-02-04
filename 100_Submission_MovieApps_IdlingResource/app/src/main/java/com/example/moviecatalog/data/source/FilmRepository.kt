package com.example.moviecatalog.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalog.data.source.local.*
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.data.source.remote.response.*

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource):FilmDataSource{

    companion object{
        @Volatile
        private var filmInstance:FilmRepository? = null

        fun getInstanceFilm(remoteData:RemoteDataSource): FilmRepository {
            return filmInstance ?: synchronized(this){
                filmInstance ?: FilmRepository(remoteData)
            }
        }
    }

    override fun getAllMovies(): LiveData<List<MovieEntitys>> {
        val movieResults = MutableLiveData<List<MovieEntitys>>()

        remoteDataSource.getMovie(object : RemoteDataSource.LoadMoviePopCallback {
            override fun onAllLoadMoviePop(moviePopResponse: List<ResultsItemMovie?>) {
                val moviePopList = ArrayList<MovieEntitys>()
                for (response in moviePopResponse){
                    val moviePop = MovieEntitys(
                        response?.releaseDate,
                        response?.popularity,
                        response?.id,
                        response?.title,
                        response?.posterPath
                    )
                    moviePopList.add(moviePop)
                }
                movieResults.postValue(moviePopList)
            }
        })
        return movieResults
    }

    override fun getAllTv(): LiveData<List<TvEntitys>> {
        val tvResults = MutableLiveData<List<TvEntitys>>()

        remoteDataSource.getTv(object : RemoteDataSource.LoadTVPopCallback{
            override fun onAllLoadTvPop(tvPopResponse: List<ResultsItemTV?>) {
                val tvPopList = ArrayList<TvEntitys>()

                for (response in tvPopResponse){
                    val tvPop = TvEntitys(
                        response?.firstAirDate,
                        response?.overview,
                        response?.popularity,
                        response?.name,
                        response?.id,
                        response?.posterPath,
                    )
                    tvPopList.add(tvPop)
                }
                tvResults.postValue(tvPopList)
            }
        })
        return tvResults
    }

    override fun getMovieDetail(movieID: Int?): LiveData<MovieDetailEntity> {
        val movieDetail = MutableLiveData<MovieDetailEntity>()

        if (movieID != null) {
            remoteDataSource.getMovieDetail(movieID, object : RemoteDataSource.LoadMovieDetailCallback{
                override fun onDetailLoadMovie(movieDetailResponse: MovieDetailResponse) {
                    val movieList = MovieDetailEntity(
                        movieDetailResponse.overview,
                        movieDetailResponse.title,
                        movieDetailResponse.posterPath,
                        movieDetailResponse.releaseDate,
                        movieDetailResponse.popularity,
                        movieDetailResponse.id,
                        movieDetailResponse.original_title
                    )
                    movieDetail.postValue(movieList)
                }
            })
        }
        return movieDetail
    }

    override fun getTvDetail(tvId: Int?): LiveData<TvDetailEntity> {
        val tvDetail = MutableLiveData<TvDetailEntity>()

        remoteDataSource.getTvDetail(tvId, object : RemoteDataSource.LoadTvDetailCallback{
            override fun onDetailLoadTV(tvDetailResponse: TvDetailResponse) {
                val tvList = TvDetailEntity(
                    tvDetailResponse.firstAirDate,
                    tvDetailResponse.overview,
                    tvDetailResponse.posterPath,
                    tvDetailResponse.originalName,
                    tvDetailResponse.popularity,
                    tvDetailResponse.id
                )
                tvDetail.postValue(tvList)
            }
        })
        return tvDetail
    }

    override fun getMovieGenre(movieID: Int?): LiveData<List<GenreMovieDetailEntity>> {
        val genreMovieDetail = MutableLiveData<List<GenreMovieDetailEntity>>()

        remoteDataSource.getGenreMovieDetail(movieID, object : RemoteDataSource.LoadGenreDetailMovie{
            override fun onGenreLoadDetailMovie(movieGenreDetail: List<GenreMovieDetailResponse?>) {
                val genreMovie = ArrayList<GenreMovieDetailEntity>()

                for (response in movieGenreDetail){
                    val genreEntity = GenreMovieDetailEntity(
                        response?.name
                    )
                    genreMovie.add(genreEntity)
                }
                genreMovieDetail.postValue(genreMovie)
            }
        })
        return genreMovieDetail
    }

    override fun getTvGenre(tvId: Int?): LiveData<List<GenreTvDetailEntity>> {
        val genreTvDetail = MutableLiveData<List<GenreTvDetailEntity>>()

        remoteDataSource.getGenreTvDetail(tvId, object : RemoteDataSource.LoadGenreDetailTv{
            override fun onGenreLoadDetailTv(tvGenreDetail: List<GenreTvDetail?>) {
                val genreTv = ArrayList<GenreTvDetailEntity>()

                for (response in tvGenreDetail){
                    val genreEntity = GenreTvDetailEntity(
                        response?.name
                    )
                    genreTv.add(genreEntity)
                }
                genreTvDetail.postValue(genreTv)
            }
        })
        return genreTvDetail
    }

    override fun getUpcomingMovie(): LiveData<List<UpcomingMovieEntity>> {
        val upcomingMovies = MutableLiveData<List<UpcomingMovieEntity>>()

        remoteDataSource.getUpcomingMovie(object : RemoteDataSource.LoadUpcomingMovie{
            override fun onUpcomingMovie(upcomingMovie: List<ResultsItemUpMovie?>) {
                val upMovies = ArrayList<UpcomingMovieEntity>()
                for (response in upcomingMovie){
                    val upMovie = UpcomingMovieEntity(
                        response?.popularity,
                        response?.voteAverage,
                        response?.id,
                        response?.title,
                        response?.posterPath
                    )
                    upMovies.add(upMovie)
                }
                upcomingMovies.postValue(upMovies)
            }
        })
        return upcomingMovies
    }

    override fun getUpcomingTv(): LiveData<List<UpcomingTvEntity>> {
        val upcomingTvs = MutableLiveData<List<UpcomingTvEntity>>()

        remoteDataSource.getUpcomingTv(object : RemoteDataSource.LoadUpcomingTv{
            override fun onUpcomingTv(upcomingTv: List<ResultsItemUpTv?>) {
                val comingsTv = ArrayList<UpcomingTvEntity>()
                for (response in upcomingTv){
                    val upTv = UpcomingTvEntity(
                        response?.popularity,
                        response?.voteAverage,
                        response?.name,
                        response?.id,
                        response?.posterPath
                    )
                    comingsTv.add(upTv)
                }
                upcomingTvs.postValue(comingsTv)
            }
        })
        return upcomingTvs
    }

    override fun getTrailerMovie(movieID: Int?): LiveData<List<MovieTrailerEntity>> {
        val trailerMovieDetail = MutableLiveData<List<MovieTrailerEntity>>()

        remoteDataSource.getTrailerMovie(movieID, object : RemoteDataSource.LoadTrailerMovie{
            override fun onTrailerMovie(trailerMovie: List<ResultsTrailerMovie?>) {
                val trailerMovies = ArrayList<MovieTrailerEntity>()
                for (response in trailerMovie){
                    val trailer = MovieTrailerEntity(
                        response?.size,
                        response?.name,
                        response?.id,
                        response?.type,
                        response?.key,
                    )
                    trailerMovies.add(trailer)
                }
                trailerMovieDetail.postValue(trailerMovies)
            }
        })
        return trailerMovieDetail
    }

    override fun getTrailerTv(tvId: Int?): LiveData<List<TvTrailerEntity>> {
        val trailerTvDetail = MutableLiveData<List<TvTrailerEntity>>()

        remoteDataSource.getTrailerTv(tvId, object: RemoteDataSource.LoadTrailerTv{
            override fun onTrailerTv(trailerTv: List<ResultsTrailerTv?>) {
                val trailerTV = ArrayList<TvTrailerEntity>()
                for (response in trailerTv){
                    val trailer = TvTrailerEntity(
                        response?.name,
                        response?.id,
                        response?.type,
                        response?.key
                    )
                    trailerTV.add(trailer)
                }
                trailerTvDetail.postValue(trailerTV)
            }
        })
        return trailerTvDetail
    }
}