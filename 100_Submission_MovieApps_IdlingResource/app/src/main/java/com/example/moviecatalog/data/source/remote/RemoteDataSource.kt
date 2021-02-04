package com.example.moviecatalog.data.source.remote

import androidx.lifecycle.LifecycleOwner
import com.example.moviecatalog.data.source.remote.response.*
import com.example.moviecatalog.utils.ApiHelper
import com.example.moviecatalog.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val lifecycleOwner: LifecycleOwner,private val apiHelper: ApiHelper){
    companion object{
        @Volatile
        private var instanceRemote:RemoteDataSource? = null

        fun getInstance(lifecycleOwner: LifecycleOwner,helper: ApiHelper):RemoteDataSource{
            return instanceRemote ?: synchronized(this){
                    instanceRemote ?: RemoteDataSource(lifecycleOwner,helper)
            }
        }
    }

    fun getMovie(callback:LoadMoviePopCallback){
        EspressoIdlingResource.increment()
        apiHelper.getMoviePop().observe(lifecycleOwner, {
            callback.onAllLoadMoviePop(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTv(callback:LoadTVPopCallback){
        EspressoIdlingResource.increment()
        apiHelper.getTv().observe(lifecycleOwner, {
            callback.onAllLoadTvPop(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getUpcomingMovie(callback:LoadUpcomingMovie){
        EspressoIdlingResource.increment()
        apiHelper.upcomingMovie().observe(lifecycleOwner, {
            callback.onUpcomingMovie(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getUpcomingTv(callback:LoadUpcomingTv){
        EspressoIdlingResource.increment()
        apiHelper.upcomingTv().observe(lifecycleOwner, {
            callback.onUpcomingTv(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getMovieDetail(movieID: Int, callback:LoadMovieDetailCallback){
        EspressoIdlingResource.increment()
        apiHelper.getMovieDetail(movieID).observe(lifecycleOwner, {
            callback.onDetailLoadMovie(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvDetail(tvID:Int?, callback:LoadTvDetailCallback){
        EspressoIdlingResource.increment()
        apiHelper.getTvDetail(tvID).observe(lifecycleOwner, {
            callback.onDetailLoadTV(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getGenreMovieDetail(movieID: Int?,callback:LoadGenreDetailMovie){
        EspressoIdlingResource.increment()
        apiHelper.getGenreDetailMovie(movieID).observe(lifecycleOwner, {
            callback.onGenreLoadDetailMovie(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getGenreTvDetail(tvID: Int?, callback:LoadGenreDetailTv){
        EspressoIdlingResource.increment()
        apiHelper.getGenreDetailTv(tvID).observe(lifecycleOwner, {
            callback.onGenreLoadDetailTv(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTrailerMovie(movieID: Int?, callbackTrailerResponse: LoadTrailerMovie){
        EspressoIdlingResource.increment()
        apiHelper.getTrailerMovie(movieID).observe(lifecycleOwner, {
            callbackTrailerResponse.onTrailerMovie(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTrailerTv(tvID: Int?, callback:LoadTrailerTv){
        EspressoIdlingResource.increment()
        apiHelper.getTrailerTv(tvID).observe(lifecycleOwner, {
            callback.onTrailerTv(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadMoviePopCallback{
        fun onAllLoadMoviePop(moviePopResponse: List<ResultsItemMovie?>)
    }

    interface LoadTVPopCallback{
        fun onAllLoadTvPop(tvPopResponse:List<ResultsItemTV?>)
    }

    interface LoadUpcomingMovie{
        fun onUpcomingMovie(upcomingMovie:List<ResultsItemUpMovie?>)
    }

    interface LoadUpcomingTv{
        fun onUpcomingTv(upcomingTv:List<ResultsItemUpTv?>)
    }

    interface LoadGenreDetailMovie{
        fun onGenreLoadDetailMovie(movieGenreDetail:List<GenreMovieDetailResponse?>)
    }

    interface LoadGenreDetailTv{
        fun onGenreLoadDetailTv(tvGenreDetail:List<GenreTvDetail?>)
    }

    interface LoadMovieDetailCallback{
        fun onDetailLoadMovie(movieDetailResponse:MovieDetailResponse)
    }

    interface LoadTvDetailCallback{
        fun onDetailLoadTV(tvDetailResponse:TvDetailResponse)
    }

    interface LoadTrailerMovie{
        fun onTrailerMovie(trailerMovie:List<ResultsTrailerMovie?>)
    }

    interface LoadTrailerTv{
        fun onTrailerTv(trailerTv:List<ResultsTrailerTv?>)
    }
}