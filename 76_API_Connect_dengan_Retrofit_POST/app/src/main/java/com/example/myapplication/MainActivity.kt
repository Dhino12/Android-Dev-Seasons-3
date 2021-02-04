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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        showPost()
        createPost()
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

        /*
        *  Callback<ArrayList<PostResponse>>
        *  Harus Sama dengan yang ada di Class API
        * */
        RetrofitClient.instance.getPosts().enqueue(object : Callback<ArrayList<PostResponse>>{
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