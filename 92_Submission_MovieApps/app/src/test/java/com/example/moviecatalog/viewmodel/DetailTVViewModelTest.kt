package com.example.moviecatalog.viewmodel

import com.example.moviecatalog.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailTVViewModelTest {
    private lateinit var viewModel : DetailTVViewModel
    private var dummyTv = DataDummy.generateDummyTV()[0]
    private val tvTitle = dummyTv.titleTv

    @Before
    fun init(){
        viewModel = DetailTVViewModel()
        viewModel.setSelectedFilms(tvTitle)
    }

    @Test
    fun getFilms() {
        viewModel.setSelectedFilms(dummyTv.titleTv)

        val tvEntity = viewModel.getFilms()

        assertNotNull(tvEntity)

        assertEquals(dummyTv.titleTv, tvEntity.titleTv)
        assertEquals(dummyTv.posterPath, tvEntity.posterPath)
        assertEquals(dummyTv.broadcast_date, tvEntity.broadcast_date)
        assertEquals(dummyTv.description, tvEntity.description)
        assertEquals(dummyTv.duration, tvEntity.duration)
        assertEquals(dummyTv.kreator, tvEntity.kreator)
        assertEquals(dummyTv.writer, tvEntity.writer)
        assertEquals(dummyTv.yearPublish, tvEntity.yearPublish)

    }

    @Test
    fun getTv(){
        val tvEntity = viewModel.getTv()
        assertNotNull(tvEntity)
        assertEquals(10,tvEntity.size.toLong())
    }
}