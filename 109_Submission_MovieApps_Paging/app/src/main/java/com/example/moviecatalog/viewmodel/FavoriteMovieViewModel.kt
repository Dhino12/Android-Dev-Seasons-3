package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity

class FavoriteMovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovieFavorite(): LiveData<PagedList<MovieDetailEntity>> = filmRepository.getFavoriteMovie()

    fun setFavorite(movieDetailEntity:MovieDetailEntity){
        val newState = !movieDetailEntity.favorite
        filmRepository.setFavoriteMovie(movieDetailEntity, newState)
    }
}