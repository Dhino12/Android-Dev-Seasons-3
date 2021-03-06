package com.example.academies.data.source.remote

import android.os.Handler
import com.example.academies.data.source.remote.response.ContentResponse
import com.example.academies.data.source.remote.response.CourseResponse
import com.example.academies.data.source.remote.response.ModuleResponse
import com.example.academies.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS:Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper)
                }
    }

    fun getAllCourses(callback:LoadCoursesCallback) {
        handler.postDelayed({ callback.onAllCoursesReceived(jsonHelper.loadCourses()) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId:String, callback:LoadModuleCallback){
        handler.postDelayed({ callback.onAllModuleReceived(jsonHelper.loadModule(courseId)) }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback:LoadContentCallback){
        handler.postDelayed( { callback.onContentReceived(jsonHelper.loadContent(moduleId)) }, SERVICE_LATENCY_IN_MILLIS )
    }

    interface LoadCoursesCallback{
        fun onAllCoursesReceived(courseResponse:List<CourseResponse>)
    }

    interface LoadModuleCallback{
        fun onAllModuleReceived(moduleResponse:List<ModuleResponse>)
    }

    interface LoadContentCallback{
        fun onContentReceived(contentResponse:ContentResponse)
    }

}

/*
* Catatan:
* penggunaan Handler untuk delay, seperti yang dilakukan di atas merupakah hal yang tidak disarankan.
* Karena proyek yang kita buat hanyalah simulasi, di mana data yang diperoleh disimulasikan berasal dari server atau API.
* Maka dari itu, penggunaan Handler pada proyek Academy digunakan untuk mensimulasikan proses asynchonous yang terjadi.
* */

