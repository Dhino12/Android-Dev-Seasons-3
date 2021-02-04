package com.example.myacademy.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AcademyViewModelTest {

    private lateinit var viewModel:AcademyViewModel

    @Before
    fun setup(){
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourse() {
        val courseEntity = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(5,courseEntity.size)
    }
}