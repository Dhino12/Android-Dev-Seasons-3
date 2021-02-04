package com.example.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntitys>>> = filmRepository.getAllMovies()

    fun getUpMovies(): LiveData<Resource<List<UpcomingMovieEntity>>> = filmRepository.getUpcomingMovie()
}