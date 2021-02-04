package com.example.myapplication.ui.reader

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.source.local.entity.ModuleEntity
import com.example.myapplication.data.source.vo.Resource
import com.example.myapplication.data.source.vo.Status
import com.example.myapplication.ui.reader.content.ModuleContentFragment
import com.example.myapplication.ui.reader.list.ModuleListFragment
import com.example.myapplication.viewmodel.ViewModelFactory

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    companion object {
        const val EXTRA_COURSE_ID = "extra_course_id"
    }

    private var isLarge = false
    private lateinit var viewModel:CourseReaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)


        if(findViewById<View>(R.id.frame_list) != null){
            isLarge = true
        }

        /*
        *.Kode di atas digunakan untuk pengecekan apakah ada frame_list atau tidak.
        * Jika  frame_list null, maka layout tersebut dimaksudkan untuk ukuran 600dp ke bawah.
        * Dan jika ada id-nya, maka layout yang digunakan untuk 600dp ke atas.
         */

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CourseReaderViewModel::class.java]

        val bundle = intent.extras
        if (bundle != null) {
            val courseId = bundle.getString(EXTRA_COURSE_ID)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)
                populateFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        if(!isLarge){
            val fragment = ModuleContentFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    // kita Akan membuat halaman CourseReaderActivity menampilkan 2 fragment
    // sekaligus jika di device yang berukuran besar seperti tablet.
    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(!isLarge){
            var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
            if (fragment == null) {
                fragment = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commit()
        }else{
            var fragmentList = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)

            if(fragmentList == null){
                fragmentList = ModuleListFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragmentList, ModuleListFragment.TAG)
            }

            var fragmentContent = supportFragmentManager.findFragmentByTag(ModuleContentFragment.TAG)
            if(fragmentContent == null){
                fragmentContent = ModuleContentFragment.newInstance()
                fragmentTransaction.add(R.id.frame_content, fragmentContent, ModuleContentFragment.TAG)
            }
            fragmentTransaction.commit()
        }
    }

    private fun removeObserver(){
        viewModel.modules.removeObserver(initObserver)
    }

    private fun getLastReadFromModules(moduleEntities:List<ModuleEntity>):String? {
        var lastReadModule:String? = null

        for(moduleEntity in moduleEntities){
            if(moduleEntity.read){
                lastReadModule = moduleEntity.moduleId
                continue
            }
            break
        }
        return lastReadModule
    }

    private val initObserver:Observer<Resource<List<ModuleEntity>>> = Observer {
        modules -> if(modules != null){
        when(modules.status){
            Status.LOADING -> {}
            Status.SUCCESS -> {
                val dataModules :List<ModuleEntity>? = modules.data
                if(dataModules != null && dataModules.isNotEmpty()){

                    val firstModule = dataModules[0]
                    val isFirstModuleRead = firstModule.read

                    if(!isFirstModuleRead){
                        val firstModuleId = firstModule.moduleId
                        viewModel.setSelectedModule(firstModuleId)
                    }else{
                        val lastReadModuleId = getLastReadFromModules(dataModules)
                        if(lastReadModuleId != null){
                            viewModel.setSelectedModule(lastReadModuleId)
                        }
                    }
                    removeObserver()
                }
            }
            Status.ERROR -> {
                Toast.makeText(this, "Gagal menampilkan data.", Toast.LENGTH_SHORT).show()
                removeObserver()
            }
        }
    }
    }
}