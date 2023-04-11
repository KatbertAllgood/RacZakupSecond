package com.example.raczakupsecond.screens.profile.profilepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.families.AllFamiliesDomain
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewMemberDomain
import com.example.domain.usecase.families.GetFamiliesUseCase
import com.example.domain.usecase.families.GetFamilyMembersUseCase
import com.example.domain.usecase.families.GetFamilyUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ProfileFragmentVM: ViewModel() {

    private val networkRepository = App.getNetworkRepository()
    private val getFamiliesUseCase = GetFamiliesUseCase(networkRepository)
    private val getFamilyUseCase = GetFamilyUseCase(networkRepository)

    private val families: MutableList<NewFamilyDomain> = mutableListOf()
    private var familiesCounting: Int = 0

    private val allFamiliesLiveData = MutableLiveData<List<NewFamilyDomain>>()
    fun getAllFamilies(): LiveData<List<NewFamilyDomain>> {
        return allFamiliesLiveData
    }

    fun getFamilies() {
        getFamiliesUseCase
            .invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<AllFamiliesDomain>() {
                override fun onSuccess(t: AllFamiliesDomain) {
                    familiesCounting = t.results.size
                    for (i in t.results) {
                        getFamily(i.id)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("FAMILIES -----", e.message.toString())
                }

            })
    }

    fun getFamily(
        familyId: Int,
    ) {
        getFamilyUseCase.invoke(familyId.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewFamilyDomain>() {
                override fun onSuccess(t: NewFamilyDomain) {
                    familiesCounting--
                    families.add(t)
                    if(familiesCounting == 0) {
                        allFamiliesLiveData.value = families
                        Log.d("LiveDataChanged", "TRUE")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("FAMILY -----", e.message.toString())
                }

            })
    }

    fun clearFamilies() {
        families.clear()
    }

}