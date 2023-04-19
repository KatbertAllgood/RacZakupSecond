package com.example.raczakupsecond.screens.profile.profilepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.families.AllFamiliesDomain
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewMemberDomain
import com.example.domain.usecase.address.DeleteAddressUseCase
import com.example.domain.usecase.address.GetAllAddressesUseCase
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
    private val getAllAddressesUseCase = GetAllAddressesUseCase(networkRepository)
    private val deleteAddressUseCase = DeleteAddressUseCase(networkRepository)

    private val families: MutableList<NewFamilyDomain> = mutableListOf()
    private var familiesCounting: Int = 0

    private val allFamiliesLiveData = MutableLiveData<List<NewFamilyDomain>>()
    fun getAllFamilies(): LiveData<List<NewFamilyDomain>> {
        return allFamiliesLiveData
    }

    private val allAddressesLiveData = MutableLiveData<List<AddressParamsDomain>>()
    fun getAllAddressesLiveData(): LiveData<List<AddressParamsDomain>> = allAddressesLiveData

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
                    Log.d("GET_FAMILIES_ERROR", e.message.toString())
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
                    Log.d("GET_FAMILY_ERROR", e.message.toString())
                }

            })
    }

    fun getAllAddresses() {
        getAllAddressesUseCase
            .invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<List<AddressParamsDomain>>(){
                override fun onSuccess(t: List<AddressParamsDomain>) {
                    allAddressesLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_ADD_ADDRESSES_ERROR", e.message.toString())
                }

            })
    }

    fun deleteAddress(addressId: String) {
        deleteAddressUseCase.invoke(addressId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AddressParamsDomain>() {
                override fun onSuccess(t: AddressParamsDomain) {
                    Log.d("DELETE_ADDRESS", t.id.toString())

                    getAllAddresses()
                }

                override fun onError(e: Throwable) {
                    Log.d("DELETE_ADDRESS_ERROR", e.message.toString())

                    getAllAddresses()
                }

            })
    }

    fun clearFamilies() {
        families.clear()
    }

}