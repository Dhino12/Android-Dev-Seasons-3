package com.example.academies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import	 om.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()

}

