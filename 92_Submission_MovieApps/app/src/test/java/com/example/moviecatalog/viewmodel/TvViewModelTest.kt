package com.example.moviecatalog.viewmodel

import com.example.moviecatalog.data.TvEntity
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvViewModelTest {

    private lateinit var viewModel:TvViewModel

    @Before
    fun init(){
        viewModel = TvViewModel()
    }

    @Test
    fun getTv() {
        val tvEntity = viewModel.getTv()
        assertNotNull(tvEntity)
        assertEquals(10, tvEntity.size)
    }
}