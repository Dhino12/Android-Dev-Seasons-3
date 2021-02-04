package com.example.academies.ui.academy

import androidx.lifecycle.ViewModel
import com.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()

}

