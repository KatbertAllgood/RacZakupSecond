package com.example.raczakupsecond.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.PhoneResponseDomain
import com.example.domain.models.PhoneDomain
import com.example.domain.usecase.auth.CheckPhoneUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginFragmentVM : ViewModel() {
    private val networkRepository = App.getNetworkRepository()
    private val checkPhoneUseCase = CheckPhoneUseCase(networkRepository)

    fun login(phone: String) {
        val phoneDomain = PhoneDomain(phone)
        checkPhoneUseCase
            .invoke(phoneDomain)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<PhoneResponseDomain>() {
                override fun onSuccess(t: PhoneResponseDomain) {
                    Log.d("LoginPhone", t.status.toString())
                }
                override fun onError(e: Throwable) {
                    Log.d("LoginPhoneERROR", e.message.toString())
                }
            })
    }
}