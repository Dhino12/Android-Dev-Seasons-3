package com.example.academies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.exa
 le.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()

}

