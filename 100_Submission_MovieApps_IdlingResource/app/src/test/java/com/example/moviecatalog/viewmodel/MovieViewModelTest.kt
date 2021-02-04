package com.example.moviecatalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.MovieEntitys
import com.example.moviecatalog.data.source.local.UpcomingMovieEntity
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
class MovieViewModelTest {

    private lateinit var viewModel:MovieViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovie:Observer<List<MovieEntitys>>

    @Mock
    private lateinit var observerUpMovie:Observer<List<UpcomingMovieEntity>>

    @Before
    fun setup(){
        viewModel = MovieViewModel(filmRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.generateDummyMovie()
        val movies = MutableLiveData<List<MovieEntitys>>()
        movies.value = dummyMovie

        `when`(filmRepository.getAllMovies()).thenReturn(movies)
        val movieEntitys = viewModel.getMovies().value
        verify(filmRepository).getAllMovies()
        assertNotNull(movieEntitys)
        assertEquals(20, movieEntitys?.size)

        viewModel.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getUpMovies(){
        val dummyUpMovie = DataDummy.generateUpcominglDummyMovie()
        val upMovie = MutableLiveData<List<UpcomingMovieEntity>>()
        upMovie.value = dummyUpMovie

        `when`(filmRepository.getUpcomingMovie()).thenReturn(upMovie)
        val upMovieEntity = viewModel.getUpMovies().value
        assertNotNull(upMovieEntity)
        assertEquals(20, upMovieEntity?.size)

        viewModel.getUpMovies().observeForever(observerUpMovie)
        verify(observerUpMovie).onChanged(dummyUpMovie)
    }
}