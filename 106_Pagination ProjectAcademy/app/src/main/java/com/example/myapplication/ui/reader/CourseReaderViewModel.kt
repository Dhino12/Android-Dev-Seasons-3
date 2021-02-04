package com.example.myapplication.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

import com.example.myapplication.data.AcademyRepository
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.vo.Resource

class CourseReaderViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    var courseId =  MutableLiveData<String>()
    var moduleId =  MutableLiveData<String>()

    fun setSelectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId.value = moduleId
    }

    var modules: LiveData<Resource<List<ModuleEntity>>> = Transformations.switchMap(courseId){
        mCourseID -> academyRepository.getAllModulesByCourse(mCourseID)
    }

    var selectedModule : LiveData<Resource<ModuleEntity>> = Transformations.switchMap(moduleId){
        selectedPosition -> academyRepository.getContent(selectedPosition)
    }

    fun readContent(module:ModuleEntity){
        academyRepository.setReadModule(module)
    }

    fun getModuleSize():Int{
        if(modules.value != null){
            val moduleEntities = modules.value?.data
            if(moduleEntities != null){
                return moduleEntities.size
            }
        }
        return 0
    }

    fun setNextPage(){
        if(selectedModule.value != null && modules.value != null){
            val modulesEntity = selectedModule.value?.data
            val moduleEntites = modules.value?.data

            if(modulesEntity != null && moduleEntites != null){
                val position = modulesEntity.position
                if(position < moduleEntites.size && position >= 0){
                    moduleId.value = moduleEntites[position + 1].moduleId
                }
            }
        }
    }

    fun setPrevPage(){
        if(selectedModule.value != null && modules.value != null){
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data

            if(moduleEntity != null && moduleEntities != null){
                val position = moduleEntity.position

                if(position < moduleEntities.size && position >= 0){
                    moduleId.value  = moduleEntities[position - 1].moduleId
                }
            }
        }
    }
}

