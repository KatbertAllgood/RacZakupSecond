package com.example.raczakupsecond.screens.auth.checkcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.CodeDomain
import com.example.domain.models.CodeResponseDomain
import com.example.domain.usecase.auth.CheckCodeUseCase
import com.example.domain.usecase.sharedpreferences.UpdatePreferenceUseCase
import com.example.domain.utils.Constants
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CheckCodeFragmentViewModel : ViewModel() {
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
                    setAccessToken(t)
                    responseSuccess.value = true
                }
                override fun onError(e: Throwable) {
                    Log.d("checkCodeERROR", e.message.toString())
                    responseSuccess.value = false
                }
            })
    }

    private fun setAccessToken(t: CodeResponseDomain) {
        val accessToken = t.accessToken
        updatePreferenceUseCase.invoke(Constants.ACCESS_TOKEN, accessToken)
    }
}