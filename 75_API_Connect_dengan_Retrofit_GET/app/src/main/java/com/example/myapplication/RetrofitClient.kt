package com.example.myretrofit

import com.example.myapplication.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Object Untuk memanggil fungsi retrofit dari mana saja
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //Instance untuk memudahkan memanggil dari mana saja
    val instance: API by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(API::class.java)
    }
}