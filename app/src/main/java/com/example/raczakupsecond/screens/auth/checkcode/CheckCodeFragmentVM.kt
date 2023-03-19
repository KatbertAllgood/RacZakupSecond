package com.example.raczakupsecond.screens.auth.checkcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.auth.CodeDomain
import com.example.domain.models.auth.CodeResponseDomain
import com.example.domain.usecase.auth.CheckCodeUseCase
import com.example.domain.usecase.sharedpreferences.UpdatePreferenceUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import com.example.data.utils.ApplicationPreferences

class CheckCodeFragmentVM : ViewModel() {
    private val networkRepository = App.getNetworkRepository()
    private val checkCodeUseCase = CheckCodeUseCase(networkRepository)

    private val sharedPreferencesRepository = App.getSharedPreferencesRepository()
    private val updatePreferenceUseCase = UpdatePreferenceUseCase(sharedPreferencesRepository)

    private val responseSuccess = MutableLiveData<Boolean>()

    fun getResponseSuccess(): LiveData<Boolean> {
        return responseSuccess
    }

    fun sendCode(
        phone: String,
        code: String
    ) {
        val codeDomain = CodeDomain(phone, code)
        checkCodeUseCase
            .invoke(codeDomain)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<CodeResponseDomain>() {
                override fun onSuccess(t: CodeResponseDomain) {
                    Log.d("checkCode", t.status.toString())
                    val user = t.user
                    Log.d(
                        "UserInfo",
                        "${user.userId}\n${user.role}\n${user.authId}"
                    )
                    setTokens(t)
                    responseSuccess.value = true
                }
                override fun onError(e: Throwable) {
                    Log.d("checkCodeERROR", e.message.toString())
                    responseSuccess.value = false
                }
            })
    }

    private fun setTokens(t: CodeResponseDomain) {
        val accessToken = t.accessToken
        ApplicationPreferences.getAccess = accessToken
        Log.d("INTERCEPTORPREFFF", ApplicationPreferences.getAccess ?: "non")
//        updatePreferenceUseCase.invoke(Constants.ACCESS_TOKEN, accessToken)
        val refreshToken = t.refreshToken
        ApplicationPreferences.getRefresh = refreshToken
//        updatePreferenceUseCase.invoke(Constants.REFRESH_TOKEN, refreshToken)
    }
}