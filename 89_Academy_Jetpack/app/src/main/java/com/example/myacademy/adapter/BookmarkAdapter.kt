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
import kotlinx.android.synthetic.main.items_bookmark.view.*

class BookmarkAdapter(private val callback:BookmarkFragmentCallback) : RecyclerView.Adapter<BookmarkAdapter.CourseViewHolder>() {

    private val listCourses = ArrayList<CourseEntity>()

    fun setCourse(course:List<CourseEntity>?){
        if(course == null) return
        listCourses.clear()
        listCourses.addAll(course)
    }

    inner class CourseViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course:CourseEntity){
            with(itemView){
                tv_item_titleBookmark.text = course.title
                tv_item_dateBookmark.text = resources.getString(R.string.deadline_date,course.deadline)
                tv_item_descriptionBookmark.text = course.description

                setOnClickListener {
                    val intent = Intent(context, DetailCourseActivity::class.java).apply {
                        putExtra(DetailCourseActivity.EXTRA_COURSE, course.courseId)
                    }
                    context.startActivity(intent)
                }

                img_share.setOnClickListener{ callback.onShareClick(course) }

                Glide.with(context)
                    .load(course.imagePath)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(img_PosterBookmark)
            }
        }
    }

    interface BookmarkFragmentCallback {
        fun onShareClick(course: CourseEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_bookmark,parent,false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(listCourses[position])
    }

    override fun getItemCount(): Int = listCourses.size
}