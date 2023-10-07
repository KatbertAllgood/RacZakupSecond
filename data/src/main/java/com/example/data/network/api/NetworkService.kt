package com.example.data.network.api

import com.example.data.network.OkHttpClient.UnsafeOkHttpClient
import com.example.data.network.api.authenticators.TokenAuthenticator
import com.example.data.network.api.interceptors.RequestInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private val okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.MINUTES)
        .addInterceptor(RequestInterceptor())
        .authenticator(TokenAuthenticator())
        .build()

    val retrofitService = Retrofit.Builder()
        .baseUrl("https://server_ip/api/v1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

}
