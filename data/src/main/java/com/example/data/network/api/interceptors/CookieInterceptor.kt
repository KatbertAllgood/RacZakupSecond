package com.example.data.network.api.interceptors

import android.util.Log
import com.example.data.utils.ApplicationPreferences
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor(
//    context: Context
) : Interceptor {
//    private val repository = SharedPreferencesRepositoryImpl(context)
//    private val updatePreferenceUseCase = UpdatePreferenceUseCase(repository)


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .build()
        val response = chain.proceed(request)
        if (response.headers("set-cookie").isNotEmpty()) {
//            updatePreferenceUseCase.invoke(Constants.SET_COOKIE, response.headers("set-cookie"))
            ApplicationPreferences.setCookie = response.headers("set-cookie").toString()
                .replace("[", "")
                .replace("]", "")

            val _cookie: String = ApplicationPreferences.setCookie ?: ""
            Log.d("COOKIE", _cookie)
        }
        return response
    }
}