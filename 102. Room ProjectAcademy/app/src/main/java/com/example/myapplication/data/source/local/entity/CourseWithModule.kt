package com.example.myapplication.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

// untuk menghubungkan course entity dengn module entity
data class CourseWithModule (
        @Embedded
        var mCourses:CourseEntity,

        @Relation(parentColumn = "courseId", entityColumn = "courseId")
        var mModule:List<ModuleEntity>
)

/*
* Coursewithmodule adalah obyek baru yang menggabungkan CoursesEntity dengan ModulesEntity.
* Ini bisa tercipta dengan memanfaatkan anotasi @Embedded dan @Relation.
* */