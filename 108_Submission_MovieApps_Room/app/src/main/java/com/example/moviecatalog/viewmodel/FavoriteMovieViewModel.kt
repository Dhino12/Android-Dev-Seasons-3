package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity

class FavoriteMovieViewModel(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovieFavorite():LiveData<List<MovieDetailEntity>> = filmRepository.getFavoriteMovie()
}