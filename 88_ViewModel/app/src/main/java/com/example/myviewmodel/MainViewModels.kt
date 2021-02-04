package com.example.myviewmodel

import androidx.lifecycle.ViewModel

class MainViewModels : ViewModel() {
    var result = 0

    fun calculate(width:String, height:String, length:String){
        result = width.toInt() * height.toInt() * length.toInt()
    }
}