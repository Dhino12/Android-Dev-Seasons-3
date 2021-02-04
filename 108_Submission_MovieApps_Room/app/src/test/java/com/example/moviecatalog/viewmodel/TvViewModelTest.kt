package com.example.moviecatalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvEntitys
import com.example.moviecatalog.data.source.local.entity.tv_entity.UpcomingTvEntity
import com.example.moviecatalog.data.source.vo.Resource
import com.example.moviecatalog.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvViewModelTest {

    private lateinit var viewModel:TvViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observerTv:Observer<Resource<List<TvEntitys>>>

    @Mock
    private lateinit var observerUpTv:Observer<Resource<List<UpcomingTvEntity>>>

    @Before
    fun setup(){
        viewModel = TvViewModel(filmRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(DataDummy.generateDummyTV())
        val tv = MutableLiveData<Resource<List<TvEntitys>>>()
        tv.value = dummyTv

        `when`(filmRepository.getAllTv()).thenReturn(tv)
        val tvEntitys = viewModel.getTvPop().value?.data
        verify(filmRepository).getAllTv()
        assertNotNull(tvEntitys)
        assertEquals(20,tvEntitys?.size)

        viewModel.getTvPop().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }

    @Test
    fun getUpcomingTv(){
        val dummyUpTv = Resource.success(DataDummy.generateUpcominglDummyTv())
        val upTv = MutableLiveData<Resource<List<UpcomingTvEntity>>>()
        upTv.value = dummyUpTv

        `when`(filmRepository.getUpcomingTv()).thenReturn(upTv)
        val upTvEntity = viewModel.getUpcomingTv().value?.data
        assertNotNull(upTvEntity)
        assertEquals(20, upTvEntity?.size)

        viewModel.getUpcomingTv().observeForever(observerUpTv)
        verify(observerUpTv).onChanged(dummyUpTv)
    }
}