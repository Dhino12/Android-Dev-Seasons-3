package com.example.myapplication.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomBarBehavior : CoordinatorLayout.Behavior<BottomNavigationView>() {
    private var height:Int? = null

    override fun onLayoutChild(parent: CoordinatorLayout, child: BottomNavigationView, layoutDirection: Int): Boolean {
        height = child.height
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, directTargetChild: View, target: View,nestedScrollAxes:Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray) {
        if(dyConsumed > 0 ){
            slideDown(child)
        }else if(dyConsumed < 0) {
            slideUp(child)
        }
    }

    fun slideUp(child:BottomNavigationView){
        child.clearAnimation()
        child.animate().translationY(0F).duration = 200
    }

    fun slideDown(child:BottomNavigationView){
        child.clearAnimation()
        child.animate().translationY(height!!.toFloat()).duration = 200
    }
}
