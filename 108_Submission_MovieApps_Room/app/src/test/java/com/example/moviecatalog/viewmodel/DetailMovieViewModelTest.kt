package com.example.moviecatalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.vo.Resource
import com.example.moviecatalog.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel:DetailMovieViewModel
    private var dummyMovie = DataDummy.generateDetailMovie()[0]
    private var movieID = dummyMovie.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var movieDetailObserver:Observer<Resource<MovieWithDetail>>

    @Before
    fun setup(){
        viewModel = DetailMovieViewModel(filmRepository)
        viewModel.setSelectedFilms(movieID)
    }

    @Test
    fun getDetail() {
        val dummyMovieWithDetail = Resource.success(DataDummy.generateDummyMovieWithDetail(dummyMovie))
        val movie = MutableLiveData<Resource<MovieWithDetail>>()
        movie.value = dummyMovieWithDetail

        `when`(filmRepository.getMovieDetail(movieID)).thenReturn(movie)

        viewModel.movieDetail.observeForever(movieDetailObserver)

        verify(movieDetailObserver).onChanged(dummyMovieWithDetail)
    }
}