package com.example.data.network.api

import com.example.data.network.api.interceptors.RefreshInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private val okHttpClient = OkHttpClient()
        .newBuilder()
//        .addInterceptor(CookieInterceptor())
        .addInterceptor(RefreshInterceptor())
        .build()

    val retrofitService = Retrofit.Builder()
        .baseUrl("http://83.220.171.139/api/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

}