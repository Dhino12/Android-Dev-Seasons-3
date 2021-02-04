package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = OneTimeWorkRequest.Builder(MyWorker::class.java).build()
        button.setOnClickListener {
            WorkManager.getInstance().enqueue(request)
        }

        val text = findViewById<TextView>(R.id.textView)
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer<WorkInfo> {
                workInfo ->
                val state = workInfo.state.name
                text.append(state + "\n")
            })
    }
}