package com.example.myapplication.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.data.source.local.entity.CourseEntity
import com.example.myapplication.data.source.vo.Status
import com.example.myapplication.ui.reader.CourseReaderActivity
import com.example.myapplication.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_course.*
import kotlinx.android.synthetic.main.content_detail_course.*

class DetailCourseActivity : AppCompatActivity() {

    internal lateinit var viewModel :DetailCourseViewModel
    private var menu:Menu? = null

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_course)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter = DetailCourseAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailCourseViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE)
            if (courseId != null) {
                viewModel.setSelectedCourse(courseId)

                progress_bar.visibility = View.VISIBLE
                viewModel.courseModule.observe(this, Observer { courseWithModule ->
                    if(courseWithModule != null){
                        when(courseWithModule.status){
                            Status.LOADING -> progress_bar.visibility = View.VISIBLE
                            Status.SUCCESS -> if(courseWithModule.data != null){
                                progress_bar.visibility = View.GONE
                                adapter.setModules(courseWithModule.data.mModule)
                                adapter.notifyDataSetChanged()
                                populateCourse(courseWithModule.data.mCourses)
                            }
                            Status.ERROR -> {
                                progress_bar.visibility = View.GONE
                                Toast.makeText(applicationContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }
        }

        with(rv_module) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun populateCourse(courseEntity: CourseEntity) {
        text_title.text = courseEntity.title
        text_desc.text = courseEntity.description
        text_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
                .load(courseEntity.imagePath)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(image_poster)

        btn_start.setOnClickListener {
            val intent = Intent(this@DetailCourseActivity, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.courseModule.observe(this, Observer {
            courseWithModule ->
                if(courseWithModule != null){
                    when(courseWithModule.status){
                        Status.LOADING -> progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> if(courseWithModule.data != null){
                            progress_bar.visibility = View.GONE
                            val state = courseWithModule.data.mCourses.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi Kesalahan",Toast.LENGTH_LONG).show()
                        }
                    }
                }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_bookmark){
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state:Boolean){
        if(menu == null) return
        val menuItem =  menu?.findItem(R.id.action_bookmark)
        if(state){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white)
        }else{
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        }
    }

}


