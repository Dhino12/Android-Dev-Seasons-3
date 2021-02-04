package com.example.myapplication

import com.google.gson.annotations.SerializedName

//Menampung data dari Response API
data class PostResponse (
    val id:Int,
    val title:String?,
    @SerializedName("body")
    val text:String?
)

//Variable diKotlin Class Harus Sama dengan JSON Variable
//SerializedName("nama variable di JSON")
//SerializedName = untuk mengubah variable yang ada di JSON dengan KotlinClass
