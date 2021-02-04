package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = MainViewModel(CuboidModel())

        btn_save.setOnClickListener(this)
        btn_calculate_volume.setOnClickListener(this)
        btn_calculate_circumference.setOnClickListener(this)
        btn_calculate_surface_area.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length = edt_Length.text.toString().trim()
        val width = edt_width.text.toString().trim()
        val height = edt_height.text.toString().trim()
        when{
            length.isEmpty() -> edt_Length.error = "Field Ini Tidak Boleh Kosong"
            height.isEmpty() -> edt_height.error = "Field Ini Tidak Boleh Kosong"
            width.isEmpty() -> edt_width.error = "Field Ini Tidak Boleh Kosong"
            else -> {
                val l = length.toDouble()
                val h = height.toDouble()
                val w = width.toDouble()

                when(v?.id){
                    R.id.btn_save -> {
                        mainViewModel.save(w,l,h)
                        visible()
                    }
                    R.id.btn_calculate_volume -> {
                        tv_Result.text = mainViewModel.getVolume().toString()
                        gone()
                    }
                    R.id.btn_calculate_circumference -> {
                        tv_Result.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                    R.id.btn_calculate_surface_area -> {
                        tv_Result.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }
                }
            }
        }
    }

    fun visible(){
        btn_calculate_volume.visibility = View.VISIBLE
        btn_calculate_surface_area.visibility = View.VISIBLE
        btn_calculate_circumference.visibility = View.VISIBLE
        btn_save.visibility = View.GONE
    }
    fun gone(){
        btn_calculate_volume.visibility = View.GONE
        btn_calculate_surface_area.visibility = View.GONE
        btn_calculate_circumference.visibility = View.GONE
        btn_save.visibility = View.VISIBLE
    }
}