package com.example.academies.ui.reader

import androidx.lifecycle.ViewModel
import com.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.ModuleEntity
import java.util.*

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)
}

