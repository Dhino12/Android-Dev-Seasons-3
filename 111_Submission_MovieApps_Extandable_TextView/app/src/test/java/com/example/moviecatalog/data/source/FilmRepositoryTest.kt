package com.example.moviecatalog.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalog.data.source.local.LocalDataSource
import com.example.moviecatalog.data.source.local.entity.combined_entity.MovieWithDetail
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieDetailEntity
import com.example.moviecatalog.data.source.local.entity.movie_entity.MovieEntitys
import com.example.moviecatalog.data.source.local.entity.movie_entity.UpcomingMovieEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.data.source.remote.RemoteDataSource
import com.example.moviecatalog.data.source.vo.Resource
import com.example.moviecatalog.utils.AppExecutors
import com.example.moviecatalog.utils.DataDummy
import com.example.moviecatalog.utils.LiveDataTestUtil
import com.example.moviecatalog.utils.PagedListUtil
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote,local,appExecutor)

    private val movieResponse = DataDummy.generateDummyRemoteMovie()

    private val tvResponse = DataDummy.generateRemoteDummyTV()

    private val upMovieResponse = DataDummy.generateRemoteUpcominglDummyMovie()

    private val upTvResponse = DataDummy.generateRemoteUpcominglDummyTv()

    private val movieDetailResponse = DataDummy.generateRemoteDetailMovie()[0]
    private val movieDetailID = movieDetailResponse.id

    private val tvDetailResponse = DataDummy.generateRemoteDetailDummyTV()[0]
    private val tvDetailID = tvDetailResponse.id

    @Test
    fun getAllMovies() {
        val dummyMovie = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntitys>
        `when`(local.getAllMovie()).thenReturn(dummyMovie)
        filmRepository.getAllMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovie()
        assertNotNull(movieEntity.data)
        assertEquals(movieResponse.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTv() {
        val dummyTv = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntitys>
        `when`(local.getAllTv()).thenReturn(dummyTv)
        filmRepository.getAllTv()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTV()))
        verify(local).getAllTv()
        assertNotNull(tvEntity)
        assertEquals(tvResponse.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyEntity = MutableLiveData<MovieWithDetail>()
        dummyEntity.value = DataDummy.generateDummyMovieWithDetail(DataDummy.generateDetailMovie()[0])
        `when`(local.getDetailMovieWithID(movieDetailID)).thenReturn(dummyEntity)

        val movieDetailEntity = LiveDataTestUtil.getValue(filmRepository.getMovieDetail(movieDetailID))
        verify(local).getDetailMovieWithID(movieDetailID)
        assertNotNull(movieDetailEntity)
        assertNotNull(movieDetailEntity.data?.mMovieDetail?.title)
        assertEquals(movieDetailResponse.title, movieDetailEntity.data?.mMovieDetail?.title)
    }

    @Test
    fun getTvDetail(){
        val dummyEntity = MutableLiveData<TvWithDetail>()
        dummyEntity.value = DataDummy.generateDummyTvWithDetail(DataDummy.generateDetailDummyTV()[0])
        `when`(local.getDetailTvWithID(tvDetailID!!)).thenReturn(dummyEntity)

        val tvDetailEntity = LiveDataTestUtil.getValue(filmRepository.getTvDetail(tvDetailID))
        verify(local).getDetailTvWithID(tvDetailID)

        assertNotNull(tvDetailEntity.data)
        assertNotNull(tvDetailEntity.data?.mTvDetail?.overview)
        assertEquals(tvDetailResponse.originalName, tvDetailEntity.data?.mTvDetail?.originalName)
    }

    @Test
    fun getUpcomingMovie(){
        val dummyUpMovie = MutableLiveData<List<UpcomingMovieEntity>>()
        dummyUpMovie.value = DataDummy.generateUpcominglDummyMovie()
        `when`(local.getUpcomingMovie()).thenReturn(dummyUpMovie)

        val upMovieEntity = LiveDataTestUtil.getValue(filmRepository.getUpcomingMovie())
        verify(local).getUpcomingMovie()

        assertEquals(upMovieResponse.size.toLong(), upMovieEntity.data?.size?.toLong())
    }

    @Test
    fun getUpcomingTv(){
        val dummyUpTv = MutableLiveData<List<UpcomingTvEntity>>()
        dummyUpTv.value = DataDummy.generateUpcominglDummyTv()
        `when`(local.getUpComingTv()).thenReturn(dummyUpTv)

        val upTvEntity = LiveDataTestUtil.getValue(filmRepository.getUpcomingTv())
        verify(local).getUpComingTv()

        assertEquals(upTvEntity.data?.size?.toLong(), upTvResponse.size.toLong())
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieDetailEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteMovie()

        val movieDetailEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDetailMovie()))

        verify(local).getFavoriteMovie()

        assertNotNull(movieDetailEntity)
    }

    @Test
    fun getFavoriteTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvDetailEntity>
        `when`(local.getFavoriteTV()).thenReturn(dataSourceFactory)
        filmRepository.getFavoriteTV()

        val tvDetailEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDetailDummyTV()))

        verify(local).getFavoriteTV()

        assertNotNull(tvDetailEntity)
    }
}