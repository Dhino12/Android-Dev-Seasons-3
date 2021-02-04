package com.example.moviecatalog.viewmodel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class TvViewModelTest {

    private lateinit var viewModel:TvViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var filmRepository: FilmRepository

    private lateinit var pagedList:PagedList<TvEntitys>

    private lateinit var observerTv:Observer<Resource<PagedList<TvEntitys>>>

    private lateinit var observerUpTv:Observer<Resource<List<UpcomingTvEntity>>>

    @Before
    fun setup(){
        pagedList = mock(PagedList::class.java) as PagedList<TvEntitys>
        filmRepository = mock(FilmRepository::class.java)
        observerTv = mock(Observer::class.java) as Observer<Resource<PagedList<TvEntitys>>>
        observerUpTv = mock(Observer::class.java) as Observer<Resource<List<UpcomingTvEntity>>>
        viewModel = TvViewModel(filmRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(10)
        val tv = MutableLiveData<Resource<PagedList<TvEntitys>>>()
        tv.value = dummyTv

        `when`(filmRepository.getAllTv()).thenReturn(tv)
        val tvEntitys = viewModel.getTvPop().value?.data
        verify(filmRepository).getAllTv()
        assertNotNull(tvEntitys)
        assertEquals(10, tvEntitys?.size)

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