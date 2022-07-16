package com.himanshu.newsapp.newsAPI

import com.himanshu.newsapp.dataModelClass.HeadlinesResponseResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPInterface {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") countryCode: String,
        @Query("apiKey") apiKey: String,
    ): Response<HeadlinesResponseResult>

    @GET("top-headlines")
    suspend fun getSportsHeadlines(
        @Query("country") Country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
    ): Response<HeadlinesResponseResult>

    @GET("top-headlines")
    suspend fun getPoliticsHeadlines(
        @Query("country")Country: String,
        @Query("q")query: String,
        @Query("apiKey") apiKey: String
    ): Response<HeadlinesResponseResult>

    @GET("top-headlines")
    suspend fun getTechnologyHeadlines(
        @Query("country")Country: String,
        @Query("category")category: String,
        @Query("apiKey") apiKey: String
    ): Response<HeadlinesResponseResult>

    @GET("everything")
    suspend fun getCustomHeadlines(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
    ): Response<HeadlinesResponseResult>

}