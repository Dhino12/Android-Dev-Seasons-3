package com.example.myacademy.view.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myacademy.R
import com.example.myacademy.adapter.DetailCourseAdapter
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.utils.DataDummy
import com.example.myacademy.view.reader.CourseReaderActivity
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    companion object{
        val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()
        val extras = intent.extras
        val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[DetailCourseViewModel::class.java]
        if(extras != null){
            val courseId = extras.getString(EXTRA_COURSE)

            if(courseId != null){

                viewModel.setSelectedCourse(courseId)
                val modules = viewModel.getModules()
                adapter.setModules(modules)
                populateCourse(viewModel.getCourse())

                for (course in DataDummy.generateDummyCourses()){
                    if(course.courseId == courseId){
                        populateCourse(course)
                    }
                }
            }
        }

        with(rv_module){
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity){
        text_title.text = courseEntity.title
        text_desc.text = courseEntity.description
        text_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java).apply {
                putExtra(CourseReaderActivity.EXTRA_COURSES_ID, courseEntity.courseId)
            }
            startActivity(intent)
        }
    }
}