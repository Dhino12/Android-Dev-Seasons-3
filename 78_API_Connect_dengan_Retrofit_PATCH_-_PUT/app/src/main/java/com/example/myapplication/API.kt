package com.example.myapplication

import retrofit2.Call
import retrofit2.http.*

interface API {
    //Akan Menimpa BASE URL Pada Retrofit Client
    //@GET("https://jsonplaceholder.typicode.com/posts")
    //Posts = Sumber URL di https://jsonplaceholder.typicode.com/posts
    // ! Hati Hati dalam penulisan END POINT
    @GET("posts")
    //====================================================
    // fun getPosts(): Call<ArrayList<PostResponse>>
    //================ Degan Query ========================
    // fun getPosts(@Query("userId") userId: Int): Call<ArrayList<PostResponse>>
    //================ Degan Multiple Query ========================
    fun getPosts(
        @Query("userId") userId: Int,
        @Query("userId") id: Int
    ): Call<ArrayList<PostResponse>>
    //================ Degan Flexible Query ========================
    @GET("posts")
    fun getPosts(
        @QueryMap paramaters:HashMap<String,String>
    ):Call<ArrayList<PostResponse>>
    /* ================================================
    * @QueryMap paramaters:HashMap<String,String>
    * @QueryMap paramaters:HashMap<Key,Value>
    * ================================================*/

    //======================== Post =======================
    @FormUrlEncoded
    @POST("posts")
    fun createPosts(
        @Field("userId") userId:Int,
        @Field("title") title:String,
        @Field("body") text:String
    ):Call<CreateResponse>

    //===================== Manipulasi URL ==================
//    @GET("post/1/comments")
//    fun getComment():Call<ArrayList<CommentResponse>>
    @GET("post/{id}/comments")
    fun getComment(@Path("id") postId:Int):Call<ArrayList<CommentResponse>>
    //=========== NOTE =======================================================
    /* fun getComment(@Path("id") postId:Int):Call<ArrayList<CommentResponse>>
    * @Path("harus sama dengan id yang ada di GET")
    * */
    //========================================================================
    //================ Degan lebih Flexible Query ========================
    @GET
    fun getComment(@Url url:String):Call<ArrayList<CommentResponse>>

    //================= PUT AND PATCH =========================================
    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPost(
        @Path("id")id:Int,
        @Field("id") idField:Int,
        @Field("userId") userId:Int,
        @Field("title") title:String?,
        @Field("body") text:String?
    ):Call<PostResponse>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPost(
        @Path("id")id:Int,
        @Field("id") idField:Int,
        @Field("userId") userId:Int,
        @Field("title") title:String?,
        @Field("body") text:String?
    ):Call<PostResponse>
}