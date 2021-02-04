package com.example.myapplication.data.source.local

import androidx.lifecycle.LiveData
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.local.entity.CourseWithModule
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.local.room.AcademyDao

class LocalDataSource private constructor(private val mAcademyDao: AcademyDao){
    companion object{
        private val INSTANCE:LocalDataSource? = null

        fun getInstance(academyDao: AcademyDao):LocalDataSource =
                INSTANCE ?: LocalDataSource(academyDao)
    }

    fun getAllCourses():LiveData<List<CourseEntity>> = mAcademyDao.getCourse()

    fun getBookmarkedCourse():LiveData<List<CourseEntity>> = mAcademyDao.getBookmarkedCourse()

    fun getCourseWithModules(courseId:String):LiveData<CourseWithModule> =
            mAcademyDao.getCourseWithModuleById(courseId)

    fun getAllModuleByCourses(courseId: String):LiveData<List<ModuleEntity>> =
            mAcademyDao.getModuleByCourseId(courseId)

    fun insertCourses(courses:List<CourseEntity>) = mAcademyDao.insertCourses(courses)

    fun insertModules(modules:List<ModuleEntity>) = mAcademyDao.insertModules(modules)

    fun setCourseBookmark(course:CourseEntity, newState:Boolean){
        course.bookmarked = newState
        mAcademyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId:String):LiveData<ModuleEntity> =
            mAcademyDao.getModuleById(moduleId)

    fun updateContent(content:String, moduleId:String){
        mAcademyDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module:ModuleEntity){
        module.read = true
        mAcademyDao.updateModule(module)
    }
}