package com.example.myacademy.view.detail

import androidx.lifecycle.ViewModel
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.modelData.ModuleEntity
import com.example.myacademy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {
    private lateinit var courseId: String
    fun setSelectedCourse(courseId : String){
        this.courseId = courseId
    }

    fun getCourse() : CourseEntity {
        lateinit var course:CourseEntity
        val courseEntities = DataDummy.generateDummyCourses()
        for (courseEntity in courseEntities){
            if(courseEntity.courseId == courseId){
                course = courseEntity
            }
        }
        return course
    }

    fun getModules() : List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}