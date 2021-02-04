package com.example.moviecatalog.networking

import com.example.moviecatalog.BuildConfig
import com.example.moviecatalog.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object{
        private const val API_KEY = BuildConfig.API_KEY_FILM
        private const val LANGUAGE = "en-US"
    }

    @GET("movie/popular?api_key=${API_KEY}&language=${LANGUAGE}&page=1")
    fun getMoviePopular(): Call<MovieResponse>

    @GET("tv/popular?api_key=${API_KEY}&language=${LANGUAGE}&page=1")
    fun getTVPopular() : Call<TVResponse>

    @GET("movie/{movie_id}?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int?): Call<MovieDetailResponse>

    @GET("tv/{tv_id}?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getTvDetail(@Path("tv_id")tv_id:Int?):Call<TvDetailResponse>

    @GET("movie/upcoming?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getMovieUpcoming() : Call<UpcomingMovieResponse>

    @GET("tv/airing_today?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getTvUpComing():Call<UpcomingTvResponse>

    @GET("movie/{movie_id}/videos?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getTrailerMovie(@Path("movie_id") movie_id: Int?):Call<MovieTrailerResponse>

    @GET("tv/{tv_id}/videos?api_key=${API_KEY}&language=${LANGUAGE}")
    fun getTrailerTv(@Path("tv_id") tv_id:Int?): Call<TvTrailerResponse>
}

