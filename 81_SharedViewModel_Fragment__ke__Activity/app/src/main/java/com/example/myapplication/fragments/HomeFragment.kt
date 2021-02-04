package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.viewmodel.DummyViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var dummyViewModel: DummyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dummyViewModel = ViewModelProvider(activity!!).get(DummyViewModel::class.java)
        view.btn_Inc_fragment.setOnClickListener {
            dummyViewModel.increment()
        }

        dummyViewModel.listenNum().observe(viewLifecycleOwner, Observer {
            view.tv_num.text = it.toString()
        })

        view.btn_change_fr.setOnClickListener {
            dummyViewModel.changeActivityButtonText("Text Baru Dikirim Dari Fragment")
        }
    }

}