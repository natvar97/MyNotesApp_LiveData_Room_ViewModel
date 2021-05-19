package com.example.mynotesapp.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("top-headlines")
    fun getNews(
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String
    ) : Observable<News>

}