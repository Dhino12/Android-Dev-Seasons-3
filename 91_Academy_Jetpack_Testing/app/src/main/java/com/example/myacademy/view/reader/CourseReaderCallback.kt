package com.example.myacademy.view.reader


//CourseReaderCallback nantinya akan digunakan untuk pindah dari halaman satu ke halaman lain.
interface CourseReaderCallback {
    fun moveTo(position:Int, moduleId:String)
}