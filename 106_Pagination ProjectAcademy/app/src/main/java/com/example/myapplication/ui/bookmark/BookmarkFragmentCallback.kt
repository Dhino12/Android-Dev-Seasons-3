package com.example.myapplication.ui.bookmark


import com.example.myapplication.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

