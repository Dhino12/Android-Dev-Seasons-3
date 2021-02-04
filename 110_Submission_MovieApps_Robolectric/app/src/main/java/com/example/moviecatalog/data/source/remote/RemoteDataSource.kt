package com.example.moviecatalog.data.source.remote

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getMovie():LiveData<ApiResponse<List<ResultsItemMovie>>>{
        EspressoIdlingResource.increment()
        val resultItemMovie = MutableLiveData<ApiResponse<List<ResultsItemMovie>>>()
        apiHelper.getMoviePop().observe(lifecycleOwner, {
            resultItemMovie.value = ApiResponse.success(it)

            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultItemMovie
    }

    fun getTv():LiveData<ApiResponse<List<ResultsItemTV>>>{
        EspressoIdlingResource.increment()
        val resultItemTv = MutableLiveData<ApiResponse<List<ResultsItemTV>>>()

        apiHelper.getTv().observe(lifecycleOwner, {
            resultItemTv.value = ApiResponse.success(it)

            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultItemTv
    }

    fun getUpcomingMovie():LiveData<ApiResponse<List<ResultsItemUpMovie>>>{
        EspressoIdlingResource.increment()
        val resultUpcomingMovie = MutableLiveData<ApiResponse<List<ResultsItemUpMovie>>>()
        apiHelper.upcomingMovie().observe(lifecycleOwner, {
            resultUpcomingMovie.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultUpcomingMovie
    }

    fun getUpcomingTv():LiveData<ApiResponse<List<ResultsItemUpTv>>>{
        EspressoIdlingResource.increment()
        val resultUpcomingTv = MutableLiveData<ApiResponse<List<ResultsItemUpTv>>>()
        apiHelper.upcomingTv().observe(lifecycleOwner, {
            resultUpcomingTv.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultUpcomingTv
    }

    fun getMovieDetail(movieID: Int?):LiveData<ApiResponse<MovieDetailResponse>>{
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<MovieDetailResponse>>()

        apiHelper.getMovieDetail(movieID).observe(lifecycleOwner, {
            resultMovieResponse.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovieResponse
    }

    fun getTvDetail(tvID:Int?):LiveData<ApiResponse<TvDetailResponse>>{
        EspressoIdlingResource.increment()
        val resultTvResponse = MutableLiveData<ApiResponse<TvDetailResponse>>()

        apiHelper.getTvDetail(tvID).observe(lifecycleOwner, {
            resultTvResponse.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvResponse
    }

    fun getTrailerMovie(movieID: Int?):LiveData<ApiResponse<List<ResultsTrailerMovie>>>{
        EspressoIdlingResource.increment()
        val trailerMovie = MutableLiveData<ApiResponse<List<ResultsTrailerMovie>>>()
        apiHelper.getTrailerMovie(movieID).observe(lifecycleOwner, {
            trailerMovie.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return trailerMovie
    }

    fun getTrailerTv(tvID: Int?):LiveData<ApiResponse<List<ResultsTrailerTv>>>{
        EspressoIdlingResource.increment()
        val trailerTv = MutableLiveData<ApiResponse<List<ResultsTrailerTv>>>()
        apiHelper.getTrailerTv(tvID).observe(lifecycleOwner, {
            trailerTv.value = ApiResponse.success(it)
            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
        return trailerTv
    }

    fun getMovieSearch(query:String, callback: LoadMovieSearchCallback){
        EspressoIdlingResource.increment()

        apiHelper.getMovieSearch(query).observe(lifecycleOwner, {
            callback.onAllLoadMovieSearch(it)

            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvSearch(query: String, callback:LoadTvSearchCallback){
        EspressoIdlingResource.increment()

        apiHelper.getTvSearch(query).observe(lifecycleOwner, {
            callback.onAllLoadTvSearch(it)

            if(!EspressoIdlingResource.espressoTestIdlingResource.isIdleNow){
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadTvSearchCallback{
        fun onAllLoadTvSearch(tvSearch:List<ResultsItemSearchTv>)
    }

    interface LoadMoviePopCallback{
        fun onAllLoadMoviePop(moviePopResponse: List<ResultsItemMovie?>)
    }

    interface LoadMovieSearchCallback{
        fun onAllLoadMovieSearch(movieSearch: List<ResultsItemSearchMovie>)
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