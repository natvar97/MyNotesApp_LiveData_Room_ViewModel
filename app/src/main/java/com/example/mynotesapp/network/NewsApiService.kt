package com.example.mynotesapp.network

import com.example.mynotesapp.Constants
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService {

    private val newsApi : NewsApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(NewsApi::class.java)

    fun getNews() : Observable<News> {
        return newsApi.getNews(
            Constants.SOURCES,
            Constants.API_KEY
        )
    }

}