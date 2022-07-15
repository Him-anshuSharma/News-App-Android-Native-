package com.himanshu.newsapp.newsAPI

import com.himanshu.newsapp.dataModelClass.Article
import com.himanshu.newsapp.dataModelClass.HeadlinesResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface newsAPIinterface {

    val apikey: String
    val articleURL: String

    @GET("top-headlines?country=in&apiKey=16d2ba05ce0644e0be6536b97767fed1")
    suspend fun getHeadlines() : Response<HeadlinesResponseResult>
}