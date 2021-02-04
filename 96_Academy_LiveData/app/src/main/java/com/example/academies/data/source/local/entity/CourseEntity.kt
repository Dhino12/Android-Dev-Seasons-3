package com.example.academies.data.source.local.entity

data class CourseEntity(
        var courseId: String,
        var title: Stri ,
        var description: String,
        var deadline: String,
        var bookmarked: Boolean = false,
        var imagePath: String
)
