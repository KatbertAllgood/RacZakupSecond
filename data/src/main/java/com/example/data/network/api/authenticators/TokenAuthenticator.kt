package com.example.data.network.api.authenticators

import android.util.Log
import com.example.data.repository.NetworkRepositoryImpl
import com.example.data.utils.InterceptorsPreferences
import com.example.domain.models.RefreshResponseDomain
import com.example.domain.usecase.auth.RefreshUseCase
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

                    InterceptorsPreferences.setAccess = t.accessToken
                    InterceptorsPreferences.setRefresh = t.refreshToken

                    Log.d("AuthenticatorRefresh", "a:${t.accessToken}\nr:${t.refreshToken}\nr:${t.data.userDto.role}")
                }
                override fun onError(e: Throwable) {
                    Log.d("AuthenticatorRefresh", e.message.toString())
                }

            })

        val accessToken: String = InterceptorsPreferences.setAccess ?: ""
        val refreshToken: String = InterceptorsPreferences.setRefresh ?: ""

        return response.request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()
    }
}