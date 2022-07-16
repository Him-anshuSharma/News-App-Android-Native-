package com.himanshu.newsapp.retrofitInstance

import com.himanshu.newsapp.newsAPI.NewsAPInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {
    val api: NewsAPInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPInterface::class.java)
    }
}