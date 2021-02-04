package com.example.myacademy.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myacademy.R
import com.example.myacademy.adapter.SectionsPageAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionsPageAdapter = SectionsPageAdapter(this,supportFragmentManager)
        view_pager.adapter  = sectionsPageAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }
}