package com.example.data.network.api.interceptors

import com.example.data.utils.ApplicationPreferences
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken: String = ApplicationPreferences.getAccess ?: ""
        val refreshToken: String = ApplicationPreferences.getRefresh ?: ""

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()
        return chain.proceed(request)
    }
}