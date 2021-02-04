package com.example.moviecatalog.viewmodel

import com.example.moviecatalog.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {
    private lateinit var viewModel:DetailMovieViewModel
    private var dummyMovie = DataDummy.generateDummyMovie()[0]
    private var movieTitle = dummyMovie.titleMovie

    @Before
    fun init(){
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedFilms(movieTitle)

    }

    @Test
    fun getFilms() {
        viewModel.setSelectedFilms(dummyMovie.titleMovie)

        val movieEntity = viewModel.getFilms()

        assertNotNull(movieEntity)

        assertEquals(dummyMovie.titleMovie, movieEntity.titleMovie)
        assertEquals(dummyMovie.posterPath, movieEntity.posterPath)
        assertEquals(dummyMovie.dateRelease, movieEntity.dateRelease)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.screenPlay, movieEntity.screenPlay)
        assertEquals(dummyMovie.director, movieEntity.director)
        assertEquals(dummyMovie.yearPublish, movieEntity.yearPublish)
    }

    @Test
    fun getTv(){
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(10,movieEntity.size.toLong())
    }
}