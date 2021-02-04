package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var edtValue1:TextView
    lateinit var edtValue2:TextView
    lateinit var edtValue3:TextView
    lateinit var cuboidViewModel: CuboidViewModel
    var saveResult:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculate = findViewById<Button>(R.id.btn_Calculate)
        val btnCirumFerence = findViewById<Button>(R.id.btn_cirumference)
        val btnCalculateVolume = findViewById<Button>(R.id.btn_calculateVolume)
        val btnSurfaceArea = findViewById<Button>(R.id.btn_calculateSurfaceArea)

        cuboidViewModel = CuboidViewModel(CuboidModel())
        edtValue1 = findViewById(R.id.edt_value1)
        edtValue2 = findViewById(R.id.edt_value2)
        edtValue3 = findViewById(R.id.edt_value3)
        btnCalculate.setOnClickListener(this)
        btnCirumFerence.setOnClickListener(this)
        btnCalculateVolume.setOnClickListener(this)
        btnSurfaceArea.setOnClickListener(this)

        if(savedInstanceState != null){
            val resultSave = savedInstanceState.getString("resultSave")
            tv_Result.text = resultSave.toString()
//            saveResult = resultSave
        }
//        tv_Result.text = saveResult
    }

    override fun onClick(v: View?) {
        val panjang = edtValue1.text.toString().trim()
        val lebar = edtValue2.text.toString().trim()
        val tinggi = edtValue3.text.toString().trim()

        when{
            panjang.isEmpty() -> edtValue1.error = "Value Harus Diisi"
            lebar.isEmpty() -> edtValue2.error = "Value Harus Diisi"
            tinggi.isEmpty() -> edtValue3.error = "Value Harus Diisi"
            else -> {
                val p = panjang.toDouble()
                val l = lebar.toDouble()
                val t = tinggi.toDouble()
                when(v?.id){
                    R.id.btn_Calculate -> {
                        cuboidViewModel.save(p,l,t)
                        visible()
                    }
                    R.id.btn_calculateVolume -> {
                        saveResult = cuboidViewModel.getVolume().toString()
                        tv_Result.text = saveResult
                        gone()
                    }
                    R.id.btn_calculateSurfaceArea -> {
                        saveResult = cuboidViewModel.getSurface().toString()
                        tv_Result.text = saveResult
                        gone()
                    }
                    R.id.btn_cirumference -> {
                        saveResult = cuboidViewModel.getCirumFerence().toString()
                        tv_Result.text = saveResult
                        gone()
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Toast.makeText(this,saveResult,Toast.LENGTH_SHORT).show()

        outState.run {
            putString("resultSave",tv_Result.text.toString())
//            putString("resultSave",saveResult.toString())
        }
    }

    private fun visible(){
        btn_Calculate.visibility = View.GONE
        btn_calculateSurfaceArea.visibility = View.VISIBLE
        btn_calculateVolume.visibility = View.VISIBLE
        btn_cirumference.visibility = View.VISIBLE
    }

    private fun gone(){
        btn_Calculate.visibility = View.VISIBLE
        btn_calculateSurfaceArea.visibility = View.GONE
        btn_calculateVolume.visibility = View.GONE
        btn_cirumference.visibility = View.GONE
    }
}