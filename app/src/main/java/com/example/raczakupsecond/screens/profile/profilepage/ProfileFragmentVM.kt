package com.example.raczakupsecond.screens.profile.profilepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.families.FamilyDomain
import com.example.domain.usecase.families.GetFamiliesUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProfileFragmentVM: ViewModel() {

    private val networkRepository = App.getNetworkRepository()
    private val getFamiliesUseCase = GetFamiliesUseCase(networkRepository)

    private val allFamiliesLiveData = MutableLiveData<List<FamilyDomain>>()


    init {
        getFamilies()
    }

    fun getAllFamilies(): LiveData<List<FamilyDomain>> {
        return allFamiliesLiveData
    }

    private fun getFamilies() {
        getFamiliesUseCase
            .invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<List<FamilyDomain>>() {
                override fun onSuccess(t: List<FamilyDomain>) {
                    allFamiliesLiveData.value = t
                }
                override fun onError(e: Throwable) {
                    Log.d("FAMILIES -----", e.message.toString())
                }

            })
    }

}