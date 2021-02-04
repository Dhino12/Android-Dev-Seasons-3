package com.example.academies.ui.academy


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.academies.R
import com.example.academies.viewmodel.AcademyViewModel
import com.example.academies.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]

            val academyAdapter = AcademyAdapter()

            progress_bar.visibility = View.VISIBLE
            viewModel.getCourses().observe(this, Observer {
                courses -> progress_bar.visibility = View.GONE
                academyAdapter.setCourses(courses)
                academyAdapter.notifyDataSetChanged()
            })

            rv_academy.layoutManager = LinearLayoutManager(context)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter
        }
    }
}

