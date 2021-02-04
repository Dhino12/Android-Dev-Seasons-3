package com.example.myapplication.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AcademyRepository
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()
}

