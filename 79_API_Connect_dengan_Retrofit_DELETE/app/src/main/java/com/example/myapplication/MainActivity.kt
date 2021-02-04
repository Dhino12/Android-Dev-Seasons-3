package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()
    private val listComment = ArrayList<CommentResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        showPost()
//        createPost()
//        showComment()
        updatePost()
        deletePost()
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(
            1
        ).enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                tv_ResponseCode.text  = t.message
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                tv_ResponseCode.text = response.code().toString()
            }
        })
    }

    private fun updatePost() {
        RetrofitClient.instance.putPost(
            5,
            4,
            5,
            null,
            "Ini Text Yang diUpdate"
        ).enqueue(object : Callback<PostResponse>{
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tv_ResponseCode.text = response.code().toString()
                val responseText = "Response Code : ${response.code()}\n" +
                        "title : ${response.body()?.title}\n" +
                        "body : ${response.body()?.text}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id : ${response.body()?.id}"
                tv_ResponseCode.text = responseText 
            }

        })
    }


    private fun showComment() {
        rv_posts.setHasFixedSize(true)
        rv_posts.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getComment("posts/2/comments").enqueue(
            object : Callback<ArrayList<CommentResponse>>{
                override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                    tv_ResponseCode.text = t.message
                }

                override fun onResponse(call: Call<ArrayList<CommentResponse>>, response: Response<ArrayList<CommentResponse>>) {
                    tv_ResponseCode.text = response.code().toString()
                    response.body()?.let { listComment.addAll(it) }
                    val adapter = CommentAdapater(listComment)
                    rv_posts.adapter = adapter
                }

            }
        )
    }

    private fun createPost(){
        RetrofitClient.instance.createPosts(
            29,
            "Retrofit Latihan",
            "UjiCoba Retrofit"
        ).enqueue(object : Callback<CreateResponse>{
            override fun onFailure(call: Call<CreateResponse>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

            override fun onResponse(call: Call<CreateResponse>, response: Response<CreateResponse>) {
                val responseText = "Response Code : ${response.code()}\n" +
                        "title : ${response.body()?.title}\n" +
                        "body : ${response.body()?.text}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id : ${response.body()?.id}"
                tv_ResponseCode.text = responseText
            }

        })
    }

    private fun showPost(){

        // ============== Inisialisasi Recycler View ==================
        rv_posts.setHasFixedSize(true)
        rv_posts.layoutManager = LinearLayoutManager(this)

        //Multiple Query Flexible
        val paramaters = HashMap<String,String>()
        paramaters.put("userId","4")
        paramaters.put("id","32")

        /*
        *  Callback<ArrayList<PostResponse>>
        *  Harus Sama dengan yang ada di Class API
        * */
        RetrofitClient.instance.getPosts(paramaters).enqueue(object : Callback<ArrayList<PostResponse>>{
            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

            override fun onResponse(call: Call<ArrayList<PostResponse>>, response: Response<ArrayList<PostResponse>>) {
                //=======================================================================
                // ======== Menampilkan Response Code ke dalam text =====================
                // ========== 200 = Success | 403 = Forbidden | 404 = NotFound  ============
                val responseCode = response.code().toString()
                tv_ResponseCode.text = responseCode
                //=======================================================================

                //=======================================================================
                //Menambahkan List dengan data dari Retrofit
                response.body()?.let { list.addAll(it) }
                val adapter = PostAdapter(list)
                rv_posts.adapter = adapter
                //=======================================================================
            }

        })
    }
}