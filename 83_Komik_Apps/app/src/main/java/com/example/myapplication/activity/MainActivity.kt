package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.fragment.GenreFragment
import com.example.myapplication.fragment.HomeFragment
import com.example.myapplication.utils.BottomBarBehavior
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        curveBottomBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val layoutParams:CoordinatorLayout.LayoutParams = curveBottomBar.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomBarBehavior()
        if(savedInstanceState == null){
            curveBottomBar.selectedItemId = R.id.navigation_home
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            when (item.itemId) {
                R.id.navigation_home -> {
                    fragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_list -> {
                    fragment = GenreFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

}