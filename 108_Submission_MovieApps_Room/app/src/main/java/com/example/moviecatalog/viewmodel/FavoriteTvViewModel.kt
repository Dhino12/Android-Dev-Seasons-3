package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity

class FavoriteTvViewModel(private val filmRepository: FilmRepository) : ViewModel(){
    fun getMovieFavorite(): LiveData<List<TvDetailEntity>> = filmRepository.getFavoriteTV()
}