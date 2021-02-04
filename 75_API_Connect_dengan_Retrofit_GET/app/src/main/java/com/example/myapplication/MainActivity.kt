package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myretrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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