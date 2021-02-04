package com.example.myapplication.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.*
import com.example.myapplication.R

class LayoutMarginDecoration(spantCount:Int,spacing:Int): BaseLayoutMargin(spantCount,spacing) {

    override fun setPadding(rv:RecyclerView, margin:Int){
        super.setPadding(rv, margin)
    }

    fun setPaddings(rv:RecyclerView,top:Int,bottom:Int,left:Int,right:Int){
        super.setPaddingRv(rv,top,bottom,left,right)
    }

    override fun setOnClickLayoutMarginItemListener(listener:OnClickLayoutMarginItemListener){
        super.setOnClickLayoutMarginItemListener(listener)
    }

    override fun getItemOffsets(outRect:Rect, view:View, parent:RecyclerView, state:RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state)

        val isRTL = parent.context.resources.getBoolean(R.bool.is_right_to_left)
        var orientation:Int = OrientationHelper.VERTICAL
        var isInverse:Boolean = false

        var position = parent.getChildAdapterPosition(view)
        var spanCurrent = position % getSpanCount()

        if(parent.layoutManager is StaggeredGridLayoutManager){
            orientation = ((parent.layoutManager as StaggeredGridLayoutManager).orientation)
            isInverse = ((parent.layoutManager as StaggeredGridLayoutManager).reverseLayout)

            val lp = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
            spanCurrent = lp.spanIndex

            if (isRTL && orientation == OrientationHelper.VERTICAL){
                spanCurrent = getSpanCount() - spanCurrent - 1
            }

        }else if(parent.layoutManager is LinearLayoutManager){
            orientation = (parent.layoutManager as LinearLayoutManager).orientation
            isInverse = (parent.layoutManager as LinearLayoutManager).reverseLayout
            position = parent.getChildLayoutPosition(view) //item Position
            spanCurrent = 0

        }else if(parent.layoutManager is GridLayoutManager){
            orientation = (parent.layoutManager as GridLayoutManager).orientation
            isInverse = (parent.layoutManager as GridLayoutManager).reverseLayout

            val lp = view.layoutParams as GridLayoutManager.LayoutParams
            spanCurrent = lp.spanIndex

            if(isRTL && orientation == OrientationHelper.VERTICAL){
                spanCurrent = getSpanCount() - spanCurrent - 1
            }

        }

        if(isRTL && orientation == OrientationHelper.HORIZONTAL){
            position = state.itemCount - position - 1
        }

        setupClickLayoutMarginItem(parent.context, view, position, spanCurrent, state)
        calculateMargin(outRect,position,spanCurrent,state.itemCount,orientation,isInverse,isRTL)
    }
}