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

    private val TAG = TokenAuthenticator::class.simpleName

    override fun authenticate(route: Route?, response: Response): Request? {
        val accessToken = ApplicationPreferences.getAccess ?: ""
        if (!isRequestWithAccessToken(response) || accessToken == null || accessToken == "") {
            return null
        }
        synchronized (this) {
            val newAccessToken = ApplicationPreferences.getAccess ?: ""
            val newRefreshToken = ApplicationPreferences.getRefresh ?: ""

            if (accessToken != newAccessToken) {

                ApplicationPreferences.getAccess = newAccessToken
                ApplicationPreferences.getRefresh = newRefreshToken

                return newRequestWithAccessToken(
                    response.request,
                    newAccessToken,
                    newRefreshToken
                )
            }

            var updatedAccessToken = ""
            var updatedRefreshToken = ""

            refreshUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<RefreshResponseDomain>() {
                    override fun onSuccess(t: RefreshResponseDomain) {

                        updatedAccessToken = t.accessToken
                        updatedRefreshToken = t.refreshToken

                        ApplicationPreferences.getAccess = t.accessToken
                        ApplicationPreferences.getRefresh = t.refreshToken

                        Log.d(TAG, "a:${t.accessToken}\nr:${t.refreshToken}\nr:${t.data.userDto.role}")
                    }
                    override fun onError(e: Throwable) {
                        Log.d(TAG, e.message.toString())
                    }
                })

            return newRequestWithAccessToken(
                response.request,
                updatedAccessToken,
                updatedRefreshToken
            )
        }
    }

    private fun isRequestWithAccessToken(response: Response) : Boolean {
        val header: String? = response.request.header("Authorization")
        return (header != null) && (header.startsWith("Bearer"))
    }

    private fun newRequestWithAccessToken(
        request: Request, accessToken: String, refreshToken: String
    ) : Request {
        return request.newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .addHeader("Cookie", "refreshToken=$refreshToken")
            .build()
    }
}