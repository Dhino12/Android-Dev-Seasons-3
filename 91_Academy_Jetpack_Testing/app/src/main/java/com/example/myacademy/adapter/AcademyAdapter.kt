package com.example.myacademy.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myacademy.R
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.view.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.CourseHolder>() {

    private var listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses:List<CourseEntity>?){
        if(courses == null) return
        listCourses.clear()
        listCourses.addAll(courses)
    }

    class CourseHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        fun bind(course:CourseEntity){
            with(itemView){
                tv_item_title.text = course.title
                tv_item_description.text = course.description
                tv_item_date.text = resources.getString(R.string.deadline_date, course.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(context, DetailCourseActivity::class.java)
                        intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                        itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(img_Poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent,false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val course = listCourses[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listCourses.size
}