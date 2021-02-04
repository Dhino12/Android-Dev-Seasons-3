package com.example.myapplication.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.AcademyRepository
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.local.entity.CourseWithModule
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.vo.Resource

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    var courseId = MutableLiveData<String>()

    fun setSelectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    // Metode  Transformations.switchMap digunakan untuk mengambil data setiap kali courseId-nya berubah.
    var courseModule : LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId){
        mCourseID -> academyRepository.getCourseWithModules(mCourseID)
    }

    fun setBookmark(){
        val moduleResource = courseModule.value
        if(moduleResource != null){
            val courseWithModule = moduleResource.data

            if(courseWithModule != null){
                val courseEntity =  courseWithModule.mCourses
                val newState = !courseEntity.bookmarked
                academyRepository.setCourseBookmark(courseEntity, newState)
            }
        }
    }
}


