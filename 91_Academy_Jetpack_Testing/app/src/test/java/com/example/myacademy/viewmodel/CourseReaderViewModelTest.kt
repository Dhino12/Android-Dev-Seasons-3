package com.example.myacademy.viewmodel

import com.example.myacademy.modelData.ContentEntity
import com.example.myacademy.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CourseReaderViewModelTest {

    private lateinit var viewModel : CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Before
    fun setup(){
        viewModel = CourseReaderViewModel()
        viewModel.setSelectedCourse(courseId)
        viewModel.setSelectedModule(moduleId)

        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity(
            " <h3 class=\\\\\\\"fr-text-bordered\\\\\\\">\" + Modul 0 : Introduction + \"</h3>\n" +
                    "    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, \n" +
                    "        sed do eiusmod tempor incididunt ut\n" +
                    "        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi\n" +
                    "        ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit\n" +
                    "        esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, \n" +
                    "        sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
                    "    </p>"
        )
    }

    @Test
    fun getModules() {
        val moduleEntity = viewModel.getModules()
        assertNotNull(moduleEntity)
        assertEquals(7, moduleEntity.size.toLong())
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = viewModel.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity = moduleEntity.contentEntity
        assertNotNull(contentEntity)
        val content = contentEntity?.content
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}