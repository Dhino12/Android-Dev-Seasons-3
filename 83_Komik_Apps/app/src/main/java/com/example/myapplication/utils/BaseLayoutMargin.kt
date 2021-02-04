package com.example.myapplication.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.View.SCROLLBARS_OUTSIDE_OVERLAY
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

abstract class BaseLayoutMargin(spanCounts:Int, spacingIn: Int): RecyclerView.ItemDecoration() {
    private var marginDelegate: MarginDelegate? = null
    private var spanCount:Int = spanCounts
    private var spacing:Int = spacingIn
    private var listener:OnClickLayoutMarginItemListener? = null

    open fun setOnClickLayoutMarginItemListener(listener:OnClickLayoutMarginItemListener){
        this.listener = listener
    }

    open fun setPadding(rv:RecyclerView, @Px margin:Int){
        this.setPaddingRv(rv, margin, margin, margin, margin)
    }

    open fun setPaddingRv(rv:RecyclerView,@Px top:Int, @Px bottom:Int, @Px left:Int, @Px right:Int){
        rv.clipToPadding = false
        rv.scrollBarStyle = SCROLLBARS_OUTSIDE_OVERLAY
        rv.setPadding(left,top,right,bottom)
    }

    fun getMarginDelegate(): MarginDelegate? = marginDelegate

    fun calculateMargin(outRect:Rect,position:Int, spanCurrent:Int, itemCount:Int, orientation:Int, isReverse:Boolean, isRTL:Boolean){
        marginDelegate?.calculateMargin(outRect, position, spanCurrent, itemCount, orientation, isReverse, isRTL)
    }
    fun getSpacing(): Int = spacing
    fun getSpanCount():Int = spanCount

    fun onClickItem(context:Context, view:View, position:Int, currentSpan:Int, state:RecyclerView.State):View.OnClickListener{
        return View.OnClickListener {
            if(listener != null){
                listener?.onClick(context,view,position,currentSpan,state)
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State){
        super.getItemOffsets(outRect, view, parent, state)
    }

    fun setupClickLayoutMarginItem(context: Context,view:View,position:Int,spanCurrent:Int,state:RecyclerView.State){
        if(listener != null){
            view.setOnClickListener(onClickItem(context,view,position,spanCurrent,state))
        }
    }
}

