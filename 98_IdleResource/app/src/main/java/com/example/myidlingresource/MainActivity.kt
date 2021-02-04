package com.example.myidlingresource

import android.icu.number.Precision.increment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        *  Increment digunakan untuk menambahkan state loading dan
        *  decrement untuk mengurangi state loading-nya.
        * */
        button.setOnClickListener {
            delay1()
            delay2()
        }
    }

    private fun delay1(){
        /*
        * Sebelum memasuki proses asynchronous, Anda perlu menambahkan increment(),
        * kemudian setelah proses asynchronous selesai lakukan decrement() untuk menetralkan kembali.
        * */

        EspressoIdlingResource.increment()
        Handler().postDelayed({
            textView.text = getString(R.string.delay1)
            if(!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow){
                // Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement()
            }
        }, 2000)
    }

    private fun delay2(){
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            textView.text = getString(R.string.delay1)
            if(!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow){
                // Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement()
            }
        }, 2000)
    }
}