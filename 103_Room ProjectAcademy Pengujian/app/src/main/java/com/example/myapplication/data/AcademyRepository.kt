package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.source.local.LocalDataSource
import com.example.myapplication.data.source.local.entity.ContentEntity
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.local.entity.CourseWithModule
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.remote.ApiResponse
import com.example.myapplication.data.source.remote.RemoteDataSource
import com.example.myapplication.data.source.remote.RemoteDataSource.LoadCoursesCallback
import com.example.myapplication.data.source.remote.response.ContentResponse
import com.example.myapplication.data.source.remote.response.CourseResponse
import com.example.myapplication.data.source.remote.response.ModuleResponse
import com.example.myapplication.data.source.vo.Resource
import com.example.myapplication.utils.AppExecutor


class AcademyRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource :LocalDataSource,
        private val appExecutor: AppExecutor
) : AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData:LocalDataSource, appExecutor: AppExecutor): AcademyRepository =
                instance ?: synchronized(this) {
                    instance ?: AcademyRepository(remoteData,localData,appExecutor)
                }
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundSource<List<CourseEntity>, List<CourseResponse>>(appExecutor){
            override fun loadFromDB(): LiveData<List<CourseEntity>> =
                localDataSource.getAllCourses()

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> =
                remoteDataSource.getAllCourses()

            override fun shouldFetch(data: List<CourseEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun saveCallResult(data: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in data){
                    val course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                    )
                    courseList.add(course)
                }
                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> =
        localDataSource.getBookmarkedCourse()

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundSource<CourseWithModule, List<ModuleResponse>>(appExecutor){
            override fun loadFromDB(): LiveData<CourseWithModule> =
                localDataSource.getCourseWithModules(courseId)

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteDataSource.getModules(courseId)

            override fun shouldFetch(data: CourseWithModule?): Boolean =
                data?.mModule == null || data.mModule.isEmpty()

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data)  {
                    val course = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                    )
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object :NetworkBoundSource<List<ModuleEntity>, List<ModuleResponse>>(appExecutor){
            override fun loadFromDB(): LiveData<List<ModuleEntity>> =
                localDataSource.getAllModuleByCourses(courseId)

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteDataSource.getModules(courseId)

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean =
                    data == null || data.isEmpty()

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()

                for (response in data){
                    val course = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                    )
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundSource<ModuleEntity, ContentResponse>(appExecutor){
            override fun loadFromDB(): LiveData<ModuleEntity> =
                localDataSource.getModuleWithContent(moduleId)

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteDataSource.getContent(moduleId)

            override fun shouldFetch(data: ModuleEntity?): Boolean =
                data?.contentEntity == null

            override fun saveCallResult(data: ContentResponse) =
                localDataSource.updateContent(data.content.toString(), moduleId)
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        appExecutor.diskIO().execute{ localDataSource.setCourseBookmark(course, state) }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutor.diskIO().execute { localDataSource.setReadModule(module) }
    }

    /*
    * Anda sudah memperbarui AcademyRepository menjadi offline-online.
    * Karena semua data dari AcademyRepository dibungkus dengan kelas Resource,
    * maka Anda perlu memperbarui kode di tiap kelas ViewModel.
    * */
}

