package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.viewmodel.DummyState
import com.example.myapplication.viewmodel.DummyViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dummyViewModel: DummyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dummyViewModel = ViewModelProvider(this).get(DummyViewModel::class.java)
        btn_dec_act.setOnClickListener {
            dummyViewModel.decrement()
        }
        dummyViewModel.listenToState().observer(this, Observer {
            when(it){
                is DummyState.changeActivityButtonText -> {
                    btn_dec_act.text = it.newText
                }
            }
        })
        supportFragmentManager.beginTransaction().replace(R.id.screenContainer, HomeFragment()).commit()
    }
}