package com.example.moviecatalog.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.vo.Resource
import com.example.moviecatalog.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MovieViewModelTest {

    private lateinit var viewModel:MovieViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var filmRepository: FilmRepository

    private lateinit var observerMovie:Observer<Resource<PagedList<MovieEntitys>>>

    private lateinit var pagedList: PagedList<MovieEntitys>

    private lateinit var observerUpMovie:Observer<Resource<List<UpcomingMovieEntity>>>

    @Before
    fun setup(){
        filmRepository = mock(FilmRepository::class.java)
        observerMovie = mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntitys>>>
        pagedList = mock(PagedList::class.java) as PagedList<MovieEntitys>
        observerUpMovie = mock(Observer::class.java) as Observer<Resource<List<UpcomingMovieEntity>>>
        viewModel = MovieViewModel(filmRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntitys>>>()
        movies.value = dummyMovie

        `when`(filmRepository.getAllMovies()).thenReturn(movies)
        val movieEntitys = viewModel.getMovies().value?.data
        verify(filmRepository).getAllMovies()
        assertNotNull(movieEntitys)
        assertEquals(10, movieEntitys?.size)

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