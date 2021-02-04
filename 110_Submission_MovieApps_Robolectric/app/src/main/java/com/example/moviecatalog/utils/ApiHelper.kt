package com.example.moviecatalog.utils

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalog.R
import com.example.moviecatalog.data.source.remote.response.*
import com.example.moviecatalog.networking.ApiConfig
import com.example.moviecatalog.networking.ApiService
import com.valdesekamdem.library.mdtoast.MDToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper(private val context:Context? = null) {

    fun getMoviePop():LiveData<List<ResultsItemMovie>> {
        val moviePopular = MutableLiveData<List<ResultsItemMovie>>()

            val client = ApiConfig.getApiService().getMoviePopular()

            client.enqueue(object : Callback<MovieResponse>{
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if(response.isSuccessful){
                        moviePopular.value = response.body()!!.results
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
                }
            })
        return moviePopular
    }

    fun getTv():LiveData<List<ResultsItemTV>>{
        val tvPopular = MutableLiveData<List<ResultsItemTV>>()

        val client = ApiConfig.getApiService().getTVPopular()
        client.enqueue(object : Callback<TVResponse>{
            override fun onResponse(call: Call<TVResponse>, response: Response<TVResponse>) {
                if(response.isSuccessful ){
                    tvPopular.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<TVResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return tvPopular
    }

    fun getMovieDetail(movieID:Int?) :LiveData<MovieDetailResponse>{

        val movieDetail = MutableLiveData<MovieDetailResponse>()

        val client = ApiConfig.getApiService().getMovieDetail(movieID)
        client.enqueue(object : Callback<MovieDetailResponse>{
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if(response.isSuccessful){
                    movieDetail.value = response.body()
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return movieDetail
    }

    fun getTvDetail(tvID:Int?):LiveData<TvDetailResponse>{

        val tvDetail = MutableLiveData<TvDetailResponse>()

        val client = ApiConfig.getApiService().getTvDetail(tvID)
        client.enqueue(object : Callback<TvDetailResponse>{
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                if(response.isSuccessful){
                    tvDetail.value = response.body()
                }
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }

        })
        return tvDetail
    }

    fun upcomingMovie() :LiveData<List<ResultsItemUpMovie>>{
        val upMovie = MutableLiveData<List<ResultsItemUpMovie>>()
        val client = ApiConfig.getApiService().getMovieUpcoming()

        client.enqueue(object : Callback<UpcomingMovieResponse>{
            override fun onResponse(call: Call<UpcomingMovieResponse>, response: Response<UpcomingMovieResponse>) {
                if (response.isSuccessful){
                    upMovie.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<UpcomingMovieResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return upMovie
    }

    fun upcomingTv():LiveData<List<ResultsItemUpTv>>{
        val upTv = MutableLiveData<List<ResultsItemUpTv>>()
        val client = ApiConfig.getApiService().getTvUpComing()

        client.enqueue(object : Callback<UpcomingTvResponse>{
            override fun onResponse(call: Call<UpcomingTvResponse>, response: Response<UpcomingTvResponse>) {
                if (response.isSuccessful){
                    upTv.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<UpcomingTvResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return upTv
    }

    fun getGenreDetailMovie(movieID: Int?):LiveData<List<GenreMovieDetailResponse>>{
        val genreMovie = MutableLiveData<List<GenreMovieDetailResponse>>()
        val client = ApiConfig.getApiService().getMovieDetail(movieID)
        client.enqueue(object : Callback<MovieDetailResponse>{
            override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                if(response.isSuccessful){
                    genreMovie.value = response.body()?.genre
                }else{
                    MDToast.makeText(context, context?.getString(R.string.errorToast), 5000,MDToast.TYPE_WARNING ).show()
                }
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return genreMovie
    }

    fun getGenreDetailTv(tvID: Int?):LiveData<List<GenreTvDetail?>>{
        val genreTv = MutableLiveData<List<GenreTvDetail?>>()
        val client = ApiConfig.getApiService().getTvDetail(tvID)
        client.enqueue(object : Callback<TvDetailResponse>{
            override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                if(response.isSuccessful){
                    genreTv.value = response.body()?.genre
                }
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return genreTv
    }

    fun getTrailerMovie(movieID: Int?):LiveData<List<ResultsTrailerMovie>>{
        val trailerMovies = MutableLiveData<List<ResultsTrailerMovie>>()
        val client = ApiConfig.getApiService().getTrailerMovie(movieID)

        client.enqueue(object : Callback<MovieTrailerResponse>{
            override fun onResponse(call: Call<MovieTrailerResponse>, response: Response<MovieTrailerResponse>) {
                if (response.isSuccessful){
                    trailerMovies.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<MovieTrailerResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return trailerMovies
    }

    fun getTrailerTv(tvID: Int?):LiveData<List<ResultsTrailerTv>>{
        val trailerTv = MutableLiveData<List<ResultsTrailerTv>>()
        val client = ApiConfig.getApiService().getTrailerTv(tvID)

        client.enqueue(object : Callback<TvTrailerResponse>{
            override fun onResponse(call: Call<TvTrailerResponse>, response: Response<TvTrailerResponse>) {
                if(response.isSuccessful){
                    trailerTv.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<TvTrailerResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return trailerTv
    }

    fun getMovieSearch(query:String):LiveData<List<ResultsItemSearchMovie>>{
        val movieSearch = MutableLiveData<List<ResultsItemSearchMovie>>()
        val client = ApiConfig.getApiService().getSearchMovie(query)

        client.enqueue(object : Callback<MovieSearchResponse>{
            override fun onResponse(call: Call<MovieSearchResponse>, response: Response<MovieSearchResponse>) {
                if(response.isSuccessful){
                    movieSearch.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return movieSearch
    }

    fun getTvSearch(query:String):LiveData<List<ResultsItemSearchTv>>{
        val tvSearch = MutableLiveData<List<ResultsItemSearchTv>>()
        val client = ApiConfig.getApiService().getSearchTv(query)

        client.enqueue(object : Callback<TvSearchResponse>{
            override fun onResponse(call: Call<TvSearchResponse>, response: Response<TvSearchResponse>) {
                if(response.isSuccessful){
                    tvSearch.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<TvSearchResponse>, t: Throwable) {
                MDToast.makeText(context, context?.getString(R.string.warningToast), 5000,MDToast.TYPE_WARNING ).show()
            }
        })
        return tvSearch
    }

}