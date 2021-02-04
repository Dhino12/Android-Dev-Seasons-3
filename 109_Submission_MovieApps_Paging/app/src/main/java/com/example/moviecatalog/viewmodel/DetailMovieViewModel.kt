package com.example.moviecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieTrailerEntity
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.vo.Resource

class DetailMovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    private var movieID = MutableLiveData<Int>()

    fun setSelectedFilms(movieID:Int?){
        this.movieID.value = movieID
    }

    var movieDetail:LiveData<Resource<MovieWithDetail>> = Transformations.switchMap(movieID){
        mMovieID -> filmRepository.getMovieDetail(mMovieID)
    }

    fun getTrailer(): LiveData<Resource<MovieTrailerEntity>> = Transformations.switchMap(movieID){
        mMovieID -> filmRepository.getTrailerMovie(mMovieID)
    }

    fun setBookmark(){
        val movieResource = movieDetail.value
        if(movieResource != null){

            val movieWithDetail = movieResource.data

            if(movieWithDetail != null){
                val movieEntityDetail = movieWithDetail.mMovieDetail
                val newState = !movieEntityDetail.favorite
                filmRepository.setFavoriteMovie(movieEntityDetail, newState)
            }
        }
    }
}