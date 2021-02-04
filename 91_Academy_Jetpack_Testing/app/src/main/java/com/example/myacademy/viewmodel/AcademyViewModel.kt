package com.example.myacademy.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.utils.DataDummy

class AcademyViewModel : ViewModel() {
    fun getCourse() : List<CourseEntity> = DataDummy.generateDummyCourses()
}