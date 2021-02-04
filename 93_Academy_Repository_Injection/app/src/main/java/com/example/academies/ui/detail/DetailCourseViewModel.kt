package com.example.academies.ui.detail

import androidx.lifecycle.ViewModel
import com.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity
import com.example.academies.data.source.local.entity.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}


