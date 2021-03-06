package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    //Posts = Sumber URL di http //jsonplaceholder.typicode.com/posts
    // ! Hati Hati dalam penulisan END POINT
    @GET("posts")
    fun getPosts(): Call<ArrayList<PostResponse>>

    //======================== Post =======================
    @FormUrlEncoded
    @POST("posts")
    fun createPosts(
        @Field("userId") userId:Int,
        @Field("title") title:String,
        @Field("body") text:String
    ):Call<CreateResponse>
}