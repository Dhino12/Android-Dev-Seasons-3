package com.example.myacademy.viewmodel

import com.example.myacademy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailCourseViewModelTest {

    private lateinit var viewModel:DetailCourseViewModel
    private var dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setup(){
        viewModel = DetailCourseViewModel()
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun setSelectedCourse() {
    }

    @Test
    fun getCourse() {
        viewModel.setSelectedCourse(dummyCourse.courseId)
        val courseEntity = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModules() {
        val moduleEntity = viewModel.getModules()
        assertNotNull(moduleEntity)
        assertEquals(7, moduleEntity.size.toLong())
    }
}