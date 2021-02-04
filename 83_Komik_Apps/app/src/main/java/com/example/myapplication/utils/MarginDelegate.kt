package com.example.myapplication.utils

import android.graphics.Rect
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.recyclerview.widget.OrientationHelper

class MarginDelegate(private var spanCounts:Int ,private var spaceItems:Int) {

    fun calculateMargin(
        outRect:Rect,
        position:Int,
        spanCurrent: Int,
        itemCount:Int,
        @IntRange(from = 0,to=1) orientation:Int,
        isReverse:Boolean,
        isRTL:Boolean
    ){
        if (orientation == OrientationHelper.VERTICAL){
            outRect.left = spanCurrent * spaceItems / spanCounts
            outRect.right = spaceItems - (spanCurrent + 1) * spaceItems / spanCounts
            if(isReverse){
                if(position >= spanCounts) outRect.bottom = spaceItems
            }else{
                if(position >= spanCounts) outRect.top = spaceItems
            }
        }else if(orientation == OrientationHelper.HORIZONTAL){
            outRect.top = spanCurrent * spaceItems / spanCounts
            outRect.bottom = spaceItems - (spanCurrent + 1) * spaceItems / spanCounts
            if(isReverse){
                if(position >= spanCounts) outRect.right = spaceItems
            }else{
                if(position >= spanCounts) outRect.left = spaceItems
            }
        }
    }
}