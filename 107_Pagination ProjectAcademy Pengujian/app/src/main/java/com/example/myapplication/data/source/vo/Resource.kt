package com.example.myapplication.data.source.vo

//Kelas Resource berfungsi sebagai pembungkus data yang akan dikelola dan ditampilkan.
data class Resource<T>(val status:Status, val data:T?, val message:String?) {
    companion object{
        fun <T> success(data:T):Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(msg:String?, data:T?):Resource<T> = Resource(Status.ERROR, data,msg)

        fun <T> loading(data:T?) :Resource<T> = Resource(Status.LOADING, data, null)
    }
}