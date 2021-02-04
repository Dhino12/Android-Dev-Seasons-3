package com.example.myacademy.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myacademy.R
import com.example.myacademy.adapter.BookmarkAdapter
import com.example.myacademy.modelData.CourseEntity
import com.example.myacademy.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(),BookmarkAdapter.BookmarkFragmentCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){

            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[BookmarkViewModel::class.java]
            val course = viewModel.getBookmarks()
            val adapter = BookmarkAdapter(this)
            adapter.setCourse(course)

            with(rv_bookmark){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }

    override fun onShareClick(course: CourseEntity) {
        if(activity != null){
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan Aplikasi ini sekarang")
                setText(resources.getString(R.string.share_text,course.title))
                startChooser()
            }
        }
    }

}