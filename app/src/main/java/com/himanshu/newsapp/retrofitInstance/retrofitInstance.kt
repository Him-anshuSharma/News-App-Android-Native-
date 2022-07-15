package com.himanshu.newsapp.retrofitInstance

import com.himanshu.newsapp.newsAPI.newsAPIinterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object retrofitInstance {
    val api: newsAPIinterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(newsAPIinterface::class.java)
    }
}