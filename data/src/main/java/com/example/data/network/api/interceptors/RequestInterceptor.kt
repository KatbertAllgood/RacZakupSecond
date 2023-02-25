package com.example.data.network.api.interceptors

import android.util.Log
import com.example.data.repository.NetworkRepositoryImpl
import com.example.data.utils.InterceptorsPreferences
import com.example.domain.models.RefreshResponseDomain
import com.example.domain.usecase.auth.RefreshUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    private val networkRepository = NetworkRepositoryImpl()
    private val refreshUseCase = RefreshUseCase(networkRepository)

    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken: String = InterceptorsPreferences.setAccess ?: ""
        val refreshToken: String = InterceptorsPreferences.setRefresh ?: ""

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()
        val response = chain.proceed(request)
        if (response.code == 401) {

//            refreshUseCase.invoke(
//                "Bearer $accessToken",
//                "refreshToken=$refreshToken"
//
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : DisposableSingleObserver<RefreshResponseDomain>() {
//                    override fun onSuccess(t: RefreshResponseDomain) {
//
//                        InterceptorsPreferences.setAccess = t.accessToken
//                        InterceptorsPreferences.setRefresh = t.refreshToken
//
//                        Log.d("InterceptorRefresh", "a:${t.accessToken}\nr:${t.refreshToken}\nr:${t.data.userDto.role}")
//                    }
//                    override fun onError(e: Throwable) {
//                        Log.d("InterceptorRefresh", e.message.toString())
//                    }
//
//                })

        }
        return response
    }
}