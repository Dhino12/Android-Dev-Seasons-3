package com.example.my_apilivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter:WeatherAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        btnCity.setOnClickListener {
            val city = editCity.text.toString()

            if(city.isEmpty()) return@setOnClickListener

            mainViewModel.setWeather(city)
            showLoading(true)
        }

        mainViewModel.getWeather().observe(this, Observer {
            weatherItems -> if(weatherItems != null){
            adapter.setData(weatherItems)
            showLoading(false)
        }
        })
    }

    fun showLoading(state:Boolean){
        if(state){
            progress_Bar.visibility = View.VISIBLE
        }else{
            progress_Bar.visibility = View.GONE
        }
    }
}