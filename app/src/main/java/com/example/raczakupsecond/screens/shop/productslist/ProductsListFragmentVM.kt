package com.example.raczakupsecond.screens.shop.productslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain
import com.example.domain.usecase.packs.CreateHealthySetParamsUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProductsListFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()
    private val createHealthySetParamsUseCase = CreateHealthySetParamsUseCase(networkRepository)

    private val healthySetParamsLiveData = MutableLiveData<HealthySetParamsResponseDomain>()
    fun getHealthySetParamsLiveData() : LiveData<HealthySetParamsResponseDomain> =
        healthySetParamsLiveData

    fun createHealthySet(
        healthySetParams: HealthySetParamsRequestDomain
    ) {
        createHealthySetParamsUseCase
            .invoke(healthySetParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<HealthySetParamsResponseDomain>() {
                override fun onSuccess(t: HealthySetParamsResponseDomain) {
                    healthySetParamsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_HS_ERROR", e.message.toString())
                }

            })
    }

}