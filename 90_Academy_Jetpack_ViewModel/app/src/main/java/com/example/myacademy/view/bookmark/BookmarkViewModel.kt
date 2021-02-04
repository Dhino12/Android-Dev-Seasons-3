package com.example.myacademy.view.bookmark

import androidx.lifecycle.ViewModel
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks() : List<CourseEntity> = DataDummy.generateDummyCourses()
}