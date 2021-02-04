package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface API {
    //Posts = Sumber URL di https://jsonplaceholder.typicode.com/posts
    // ! Hati Hati dalam penulisan END POINT
    @GET("posts")
    fun getPosts(): Call<ArrayList<PostResponse>>
}