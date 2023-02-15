package com.example.raczakupsecond.screens.auth.checkcode

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.CodeDomain
import com.example.domain.models.CodeResponseDomain
import com.example.domain.usecase.CheckCodeUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CheckCodeFragmentViewModel : ViewModel() {
    private val networkRepository = App.getNetworkRepository()
    private val checkCodeUseCase = CheckCodeUseCase(networkRepository)

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
                    Log.d("checkTOKEN", t.accessToken)
                }

                override fun onError(e: Throwable) {
                    Log.d("checkCodeERROR", e.message.toString())
                }

            })
    }
}