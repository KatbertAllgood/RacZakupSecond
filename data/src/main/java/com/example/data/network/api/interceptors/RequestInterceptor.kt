package com.example.data.network.api.interceptors

import com.example.data.repository.NetworkRepositoryImpl
import com.example.data.utils.InterceptorsPreferences
import com.example.domain.usecase.auth.RefreshUseCase
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken: String = InterceptorsPreferences.setAccess ?: ""
        val refreshToken: String = InterceptorsPreferences.setRefresh ?: ""

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()
        return chain.proceed(request)
    }
}