package com.example.myapplication.data


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.local.entity.CourseWithModule
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.vo.Resource

interface AcademyDataSource {

    fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>>

    fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String):LiveData<Resource<ModuleEntity>>

    fun setCourseBookmark(course:CourseEntity, state:Boolean)

    fun setReadModule(module:ModuleEntity)
}

/*
* Di sini kita gunakan CourseWithModule pada getCourseWithModules dan hapus parameter
* courseId di getContent. Selain itu Terdapat 2 tambahan metode,
* setCourseBookmark yang berfungsi untuk menambahkan course ke daftar bookmark dan setReadModule
* digunakan untuk memperlihatkan module mana yang terakhir dibaca.
* */