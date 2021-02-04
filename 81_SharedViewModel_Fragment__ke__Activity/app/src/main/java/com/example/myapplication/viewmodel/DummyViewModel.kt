package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.SingleLiveEvent

class DummyViewModel : ViewModel() {
    var num = MutableLiveData<Int>()
    private var state: SingleLiveEvent<DummyState> = SingleLiveEvent()

    init {
        num.postValue(0)
    }

    fun increment(){
         val i = num.value!! + 1
        num.postValue(i)
    }

    fun decrement(){
        val i = num.value!! - 1
        num.postValue(i)
    }

    fun listenNum() = num
    fun listenToState() = state
    fun changeActivityButtonText(text:String){
        state.value = DummyState.changeActivityButtonText(text)
    }
}

sealed class DummyState{
    data class changeActivityButtonText(var newText:String): DummyState()
}