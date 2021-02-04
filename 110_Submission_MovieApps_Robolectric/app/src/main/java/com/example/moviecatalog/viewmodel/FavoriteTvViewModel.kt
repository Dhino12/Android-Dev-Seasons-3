package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity

class FavoriteTvViewModel(private val filmRepository: FilmRepository) : ViewModel(){
    fun getMovieFavorite(): LiveData<PagedList<TvDetailEntity>> = filmRepository.getFavoriteTV()

    fun setFavorite(tvDetailEntity: TvDetailEntity){
        val newState = !tvDetailEntity.favorit
        filmRepository.setFavoriteTV(tvDetailEntity, newState)
    }
}