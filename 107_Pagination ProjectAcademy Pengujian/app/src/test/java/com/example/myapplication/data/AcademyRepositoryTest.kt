package com.example.myapplication.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.myapplication.data.source.local.LocalDataSource
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.local.entity.CourseWithModule
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.remote.RemoteDataSource
import com.example.myapplication.data.source.vo.Resource
import com.example.myapplication.utils.AppExecutor
import com.example.myapplication.utils.DataDummy
import com.example.myapplication.utils.LiveDataTestUtil
import com.example.myapplication.utils.PagedListUtils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import org.mockito.Mockito.`when`

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutor::class.java)
    private val academyRepository = FakeAcademyRepository(remote, local, appExecutor)

    private val courseResponses = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId.toString())

    @Test
    fun getAllCourses() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CourseEntity>
        `when`(local.getAllCourses()).thenReturn(dataSourceFactory)
        academyRepository.getAllCourses()

        val courseEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyCourses()))
        verify(local).getAllCourses()
        assertNotNull(courseEntities.data)
        assertEquals(courseResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        val dummyModules = MutableLiveData<List<ModuleEntity>>()
        dummyModules.value = DataDummy.generateDummyModules(courseId)
        `when`(local.getAllModuleByCourses(courseId)).thenReturn(dummyModules)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
        verify(local).getAllModuleByCourses(courseId)

        assertNotNull(courseEntities.data)
        assertEquals(moduleResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, CourseEntity>
        `when`(local.getBookmarkedCourse()).thenReturn(dataSourceFactory)
        academyRepository.getBookmarkedCourses()

        val courseEntities = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateDummyCourses()))

        verify(local).getBookmarkedCourse()

        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }

    @Test
    fun getContent() {
        val dummyEntity = MutableLiveData<ModuleEntity>()
        dummyEntity.value = DataDummy.generateDummyModuleWithContent(moduleId)

        `when`(local.getModuleWithContent(courseId)).thenReturn(dummyEntity)

        val courseEntitiesContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId))

        verify(local).getModuleWithContent(courseId)

        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.data?.contentEntity)
        assertNotNull(courseEntitiesContent.data?.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.data?.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        val dummyEntity = MutableLiveData<CourseWithModule>()
        dummyEntity.value = DataDummy.generateDummyCourseWithModules(DataDummy.generateDummyCourses()[0],false)

        `when`(local.getCourseWithModules(courseId)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId.toString()))

        verify(local).getCourseWithModules(courseId)

        assertNotNull(courseEntities.data)
        assertEquals(courseResponses[0].title, courseEntities.data?.mCourses?.title)
    }
}