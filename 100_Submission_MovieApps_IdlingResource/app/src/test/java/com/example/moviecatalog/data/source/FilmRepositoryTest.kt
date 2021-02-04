package com.example.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.utils.DataDummy
import com.example.moviecatalog.utils.LiveDataTestUtil

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val movieResponse = DataDummy.generateDummyRemoteMovie()

    private val tvResponse = DataDummy.generateRemoteDummyTV()

    private val upMovieResponse = DataDummy.generateRemoteUpcominglDummyMovie()

    private val upTvResponse = DataDummy.generateRemoteUpcominglDummyTv()

    private val movieDetailResponse = DataDummy.generateRemoteDetailMovie()
    private val movieDetailID = movieDetailResponse.id

    private val tvDetailResponse = DataDummy.generateRemoteDetailDummyTV()
    private val tvDetailID = tvDetailResponse.id

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviePopCallback)
                .onAllLoadMoviePop(movieResponse)
            null
        }.`when`(remote).getMovie(any())
        val movieEntity = LiveDataTestUtil.getValue(filmRepository.getAllMovies())
        verify(remote).getMovie(any())
        assertNotNull(movieEntity)
        assertEquals(movieResponse.size.toLong(), movieEntity.size.toLong())
    }

    @Test
    fun getAllTv() {
        doAnswer {
            invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTVPopCallback)
                .onAllLoadTvPop(tvResponse)
            null
        }.`when`(remote).getTv(any())
        val tvEntity = LiveDataTestUtil.getValue(filmRepository.getAllTv())
        verify(remote).getTv(any())
        assertNotNull(tvEntity)
        assertEquals(tvResponse.size.toLong(), tvEntity.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        if (movieDetailID != null) {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback)
                    .onDetailLoadMovie(movieDetailResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieDetailID), any())
        }

        val movieDetailEntity = LiveDataTestUtil.getValue(filmRepository.getMovieDetail(movieDetailID))

        if (movieDetailID != null) {
            verify(remote).getMovieDetail(eq(movieDetailID),any())
        }

        assertNotNull(movieDetailEntity)
        assertNotNull(movieDetailEntity.title)
        assertEquals(movieDetailResponse.title, movieDetailEntity.title)
    }

    @Test
    fun getTvDetail(){
        doAnswer {
            invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvDetailCallback)
                .onDetailLoadTV(tvDetailResponse)
            null
        }.`when`(remote).getTvDetail(eq(tvDetailID), any())

        val tvDetailEntity = LiveDataTestUtil.getValue(filmRepository.getTvDetail(tvDetailID))

        verify(remote).getTvDetail(eq(tvDetailID), any())

        assertNotNull(tvDetailEntity)
        assertNotNull(tvDetailEntity.overview)
        assertEquals(tvDetailResponse.originalName, tvDetailEntity.originalName)
    }

    @Test
    fun getUpcomingMovie(){
        doAnswer {
            invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadUpcomingMovie)
                .onUpcomingMovie(upMovieResponse)
            null
        }.`when`(remote).getUpcomingMovie(any())

        val upMovieEntity = LiveDataTestUtil.getValue(filmRepository.getUpcomingMovie())

        verify(remote).getUpcomingMovie(any())

        assertEquals(upMovieResponse.size.toLong(), upMovieEntity.size.toLong())
    }

    @Test
    fun getUpcomingTv(){
        doAnswer {
            invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadUpcomingTv)
                .onUpcomingTv(upTvResponse)
            null
        }.`when`(remote).getUpcomingTv(any())

        val upTvEntity = LiveDataTestUtil.getValue(filmRepository.getUpcomingTv())
        verify(remote).getUpcomingTv(any())

        assertEquals(upTvEntity.size.toLong(), upTvResponse.size.toLong())
    }
}