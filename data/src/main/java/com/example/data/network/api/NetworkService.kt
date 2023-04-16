package com.example.data.network.api

import com.example.data.network.OkHttpClient.UnsafeOkHttpClient
import com.example.data.network.api.authenticators.TokenAuthenticator
import com.example.data.network.api.interceptors.RequestInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {


    private val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        .addInterceptor(RequestInterceptor())
        .authenticator(TokenAuthenticator())
        .build()

    val retrofitService = Retrofit.Builder()
        .baseUrl("https://83.220.171.139/api/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

}