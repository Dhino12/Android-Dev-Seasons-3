package com.example.moviecatalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
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
class MovieViewModelTest {

    private lateinit var viewModel:MovieViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerMovie:Observer<Resource<List<MovieEntitys>>>

    @Mock
    private lateinit var observerUpMovie:Observer<Resource<List<UpcomingMovieEntity>>>

    @Before
    fun setup(){
        viewModel = MovieViewModel(filmRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(DataDummy.generateDummyMovie())
        val movies = MutableLiveData<Resource<List<MovieEntitys>>>()
        movies.value = dummyMovie

        `when`(filmRepository.getAllMovies()).thenReturn(movies)
        val movieEntitys = viewModel.getMovies().value?.data
        verify(filmRepository).getAllMovies()
        assertNotNull(movieEntitys)
        assertEquals(20, movieEntitys?.size)

        viewModel.getMovies().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getUpMovies(){
        val dummyUpMovie = Resource.success(DataDummy.generateUpcominglDummyMovie())
        val upMovie = MutableLiveData<Resource<List<UpcomingMovieEntity>>>()
        upMovie.value = dummyUpMovie

        `when`(filmRepository.getUpcomingMovie()).thenReturn(upMovie)
        val upMovieEntity = viewModel.getUpMovies().value?.data
        assertNotNull(upMovieEntity)
        assertEquals(20, upMovieEntity?.size)

        viewModel.getUpMovies().observeForever(observerUpMovie)
        verify(observerUpMovie).onChanged(dummyUpMovie)
    }
}