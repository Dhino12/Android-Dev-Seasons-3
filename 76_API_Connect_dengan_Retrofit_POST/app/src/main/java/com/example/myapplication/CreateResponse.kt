package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class CreateResponse(
    val userId:String,
    val id:Int,
    val title:String?,
    @SerializedName("body")
    val text:String?
)