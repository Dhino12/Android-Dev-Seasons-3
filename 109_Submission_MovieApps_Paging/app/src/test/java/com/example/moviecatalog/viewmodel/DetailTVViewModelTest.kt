package com.example.moviecatalog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalog.data.source.FilmRepository
import com.example.moviecatalog.data.source.local.entity.combined_entity.TvWithDetail
import com.example.moviecatalog.data.source.local.entity.tv_entity.TvDetailEntity
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
class DetailTVViewModelTest {
    private lateinit var viewModel : DetailTVViewModel
    private var dummyTv = DataDummy.generateDetailDummyTV()[0]
    private val tvID = dummyTv.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer:Observer<Resource<TvWithDetail>>

    @Before
    fun setup(){
        viewModel = DetailTVViewModel(filmRepository)
        viewModel.setSelectedFilms(tvID)
    }

    @Test
    fun getTvDetail() {
        val dummyTvWithDetail = Resource.success(DataDummy.generateDummyTvWithDetail(dummyTv))
        val tv = MutableLiveData<Resource<TvWithDetail>>()
        tv.value = dummyTvWithDetail

        `when`(filmRepository.getTvDetail(tvID!!)).thenReturn(tv)

        viewModel.tvDetail.observeForever(observer)

        verify(observer).onChanged(dummyTvWithDetail)
    }

}