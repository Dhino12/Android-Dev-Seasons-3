package com.example.myviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModels : MainViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModels = ViewModelProvider(this)[MainViewModels::class.java]
        displayResult()

        btn_calculate.setOnClickListener {
            val width = edt_width.text.toString()
            val height = edt_height.text.toString()
            val length = edt_length.text.toString()

            when{
                width.isEmpty() -> {
                    edt_width.error = "Masih Kosong"
                }
                height.isEmpty() -> {
                    edt_height.error = "Masih Kosong"
                }
                length.isEmpty() -> {
                    edt_length.error = "Masih Kosong"
                }
                else -> {
                    viewModels.calculate(width,height, length)
                    displayResult()
                }
            }
        }
    }

    private fun displayResult(){
        tv_result.text = viewModels.result.toString()
    }
}