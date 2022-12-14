package com.mladenjovicic.newsapp.data.server

import com.mladenjovicic.newsapp.BuildConfig
import com.mladenjovicic.newsapp.data.model.server.NewsServerModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {

    @GET("everything")
    fun getServerNewsEverything(
        @Query("q") query: String?,
        @Query("sortBy") sort: String?,
        @Query("from") from:String?,
        @Query("to") to:String?,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Call<NewsServerModel>

    @GET("top-headlines")
    fun getServerNewsTopHeadlines(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("sources") sources:String?,
        @Query("domains") domains:String?,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Call<NewsServerModel>
}