package com.example.academies.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity
import com.example.academies.utils.DataDummy
import com.example.academies.viewmodel.AcademyViewModel
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
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutableRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourse = DataDummy.generateDummyCourses()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourse

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntity = viewModel.getCourses().value
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntity)
        assertEquals(5, courseEntity?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}

/*
*  observeForever digunakan untuk meng-observe secara terus menerus.
*  InstantTaskExecutorRule karena pengujiannya berupa proses asynchronous.
*  observeForever digunakan untuk meng-observe secara terus menerus.
*  onChanged digunakan untuk memastikan terjadi perubahan data di LiveData.
 */