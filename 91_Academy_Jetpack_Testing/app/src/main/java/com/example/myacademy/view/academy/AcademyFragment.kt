package com.example.myacademy.view.academy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myacademy.R
import com.example.myacademy.adapter.AcademyAdapter
import com.example.myacademy.viewmodel.AcademyViewModel
import kotlinx.android.synthetic.main.fragment_academy.*

class AcademyFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){

            val viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[AcademyViewModel::class.java]
            val course = viewModel.getCourse()

            val academyAdapter = AcademyAdapter()
            academyAdapter.setCourses(course)

            with(rv_academy){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}