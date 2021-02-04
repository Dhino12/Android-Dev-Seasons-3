package com.example.academies.ui.bookmark

import androidx.lifecycle.ViewModel
import com.example.academies.data.AcademyRepository
import com.example.academies.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()

}

