package com.example.myapplication.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity.CENTER
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.myapplication.R

class MyButton: AppCompatButton {

    private var enableBackground:Drawable? = null
    private var disableBackground:Drawable? = null
    private var textColour:Int = 0

    constructor(context:Context):super(context){
        init()
    }

    constructor(context: Context, attrs:AttributeSet):super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, difStyleAttr:Int):super(context, attrs, difStyleAttr){
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if(isEnabled) enableBackground else disableBackground
        setTextColor(textColour)
        textSize = 12f
        gravity = CENTER
    }

    private fun init(){
        val resource = resources
        enableBackground = resource.getDrawable(R.drawable.bg_button)
        disableBackground = resource.getDrawable(R.drawable.bg_button_disable)
        textColour = ContextCompat.getColor(context, android.R.color.background_light)
    }
}