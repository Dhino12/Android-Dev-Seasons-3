package com.example.myapplication.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.data.AcademyRepository
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.vo.Resource
import com.example.myapplication.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<CourseEntity>>>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourses = Resource.success(DataDummy.generateDummyCourses())
        val courses = MutableLiveData<Resource<List<CourseEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value?.data
        verify<AcademyRepository>(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}