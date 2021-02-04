package com.example.myacademy.view.reader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myacademy.R
import com.example.myacademy.view.reader.content.ModuleContentFragment
import com.example.myacademy.view.reader.list.ModuleListFragment

class CourseReaderActivity : AppCompatActivity(), CourseReaderCallback {

    companion object{
        val EXTRA_COURSES_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_reader)

        val bundle = intent.extras
        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[CourseReaderViewModel::class.java]
        if(bundle != null){
            val courseId = bundle.getString(EXTRA_COURSES_ID)
            if(courseId != null){
                viewModel.setSelectedCourse(courseId)
                populateFragment()
            }
        }
    }

    override fun moveTo(position: Int, moduleId: String) {
        val fragment = ModuleContentFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, ModuleContentFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount <= 1){
            finish()
        }else{
            super.onBackPressed()
        }
    }

    private fun populateFragment(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(ModuleListFragment.TAG)
        if(fragment == null){
            Toast.makeText(this,"Masuk IF",Toast.LENGTH_SHORT).show()
            fragment = ModuleListFragment.newInstance()
            fragmentTransaction.add(R.id.frame_container, fragment, ModuleListFragment.TAG)
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}