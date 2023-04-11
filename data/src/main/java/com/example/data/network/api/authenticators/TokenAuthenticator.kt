package com.example.data.network.api.authenticators

import android.util.Log
import com.example.data.repository.NetworkRepositoryImpl
import com.example.data.utils.ApplicationPreferences
import com.example.domain.models.auth.RefreshResponseDomain
import com.example.domain.usecase.refreshtokens.RefreshUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    private val networkRepository = NetworkRepositoryImpl()
    private val refreshUseCase = RefreshUseCase(networkRepository)

    override fun authenticate(route: Route?, response: Response): Request? {

        refreshUseCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<RefreshResponseDomain>() {
                override fun onSuccess(t: RefreshResponseDomain) {

                    ApplicationPreferences.getAccess = t.accessToken
                    ApplicationPreferences.getRefresh = t.refreshToken

                    Log.d("AuthenticatorRefresh", "a:${t.accessToken}\nr:${t.refreshToken}\nr:${t.data.userDto.role}")
                }
                override fun onError(e: Throwable) {
                    Log.d("AuthenticatorRefresh", e.message.toString())
                }

            })

        val accessToken: String = ApplicationPreferences.getAccess ?: ""
        val refreshToken: String = ApplicationPreferences.getRefresh ?: ""

        return response.request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()

    }
}




//        val token = ApplicationPreferences.getAccess ?: ""
//
//        synchronized(this) {
//            var newToken = ""
//
//            refreshUseCase.invoke()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : DisposableSingleObserver<RefreshResponseDomain>() {
//                    override fun onSuccess(t: RefreshResponseDomain) {
//
//                        newToken = t.accessToken
//
//                    }
//                    override fun onError(e: Throwable) {
//                        Log.d("AuthenticatorRefresh", e.message.toString())
//                    }
//
//                })
//
//            if(response.request.header("Authorization") != null){
//
//                if(newToken != token){
//                    return response.request
//                        .newBuilder()
//                        .removeHeader("Authorization")
//                        .addHeader("Authorization", "Bearer $newToken")
//                        .build()
//                }
//
//                val updatedToken = ApplicationPreferences.getRefresh ?: ""
//
//            }
//
//
//
//        }
//
//        if(!response.request.header("Authorization").equals(ApplicationPreferences.getAccess)) {
//            Log.d("REFRESH", "TRIED")
//            return null
//        }
//
//        refreshUseCase.invoke()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : DisposableSingleObserver<RefreshResponseDomain>() {
//                override fun onSuccess(t: RefreshResponseDomain) {
//
//                    ApplicationPreferences.getAccess = t.accessToken
//                    ApplicationPreferences.getRefresh = t.refreshToken
//
//                    Log.d("AuthenticatorRefresh", "a:${t.accessToken}\nr:${t.refreshToken}\nr:${t.data.userDto.role}")
//                }
//                override fun onError(e: Throwable) {
//                    Log.d("AuthenticatorRefresh", e.message.toString())
//                }
//
//            })
//
//        val accessToken: String = ApplicationPreferences.getAccess ?: ""
//        val refreshToken: String = ApplicationPreferences.getRefresh ?: ""
//
//        return response.request.newBuilder()
//            .addHeader("Authorization", "Bearer $accessToken")
//            .addHeader("Cookie", "refreshToken=$refreshToken")
//            .build()