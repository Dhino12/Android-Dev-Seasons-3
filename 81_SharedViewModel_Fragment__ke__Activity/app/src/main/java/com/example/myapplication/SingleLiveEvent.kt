package com.example.myapplication

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

//SingleLiveEvent = untuk UI State
class SingleLiveEvent<T> : MutableLiveData<T>() {
    companion object{
        private var TAG = "SingelLiveEvent"
    }

    private val mPending =  AtomicBoolean(false)

    @MainThread
    fun observer(owner: LifecycleOwner,observer: Observer<T>){
        if(hasActiveObservers()){
            Log.w(TAG,"Hanya satu observer yang bekerja")
        }
        super.observe(owner, Observer {
            if(mPending.compareAndSet(true,false)){
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
        //SetValue = Mengubah Value DiMainThread
        // PostValue = Mengubah/Mengisi Value DiBackgroudThrean
    }

    @MainThread
    fun call(){
        value = null
    }
}