package com.example.raczakupsecond.screens.packs.checkpack

import android.content.Context
import android.location.Address
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewMemberDomain
import com.example.domain.usecase.address.GetAddressUseCase
import com.example.domain.usecase.families.GetFamilyUseCase
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CheckPackFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()

    //region Family section

    private val getFamilyUseCase = GetFamilyUseCase(networkRepository)

    private val familyNameLiveData = MutableLiveData<String>()
    fun getFamilyNameLiveData() = familyNameLiveData

    private val membersListLiveData = MutableLiveData<List<NewMemberDomain>>()
    fun getMembersListLiveData() = membersListLiveData

    fun getFamily(
        familyId: String
    ) {

        getFamilyUseCase.invoke(
            familyId,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewFamilyDomain>() {
                override fun onSuccess(t: NewFamilyDomain) {
                    familyNameLiveData.value = t.name
                    membersListLiveData.value = t.members
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_FAMILY_CHECK_PACK", e.message.toString())
                }

            })
    }

    //endregion

    //region Days section

    private val daysLiveData = MutableLiveData<Int>()
    fun getDaysLiveData() = daysLiveData

    fun changeDaysLiveData(days: Int) { daysLiveData.value = days }

    //endregion

    //region Budget section

    private val budgetLiveData = MutableLiveData<String>()
    fun getBudgetLiveData() = budgetLiveData

    fun changeBudgetLiveData(budgetId: String) { budgetLiveData.value = budgetId }

    //endregion

    //region Address section

    private val getAddressUseCase = GetAddressUseCase(networkRepository)

//    private val addressTitleLiveData = MutableLiveData<String>()
//    fun getAddressTitleLiveData() : LiveData<AddressParamsDomain> = addressTitleLiveData

//    fun changeAddressTitleLiveData(title: String) { addressTitleLiveData.value = title }

    private val addressLiveData = MutableLiveData<AddressParamsDomain>()
    fun getAddressLiveData() : LiveData<AddressParamsDomain> = addressLiveData

    fun getAddress(
        addressId: String
    ) {
        getAddressUseCase.invoke(addressId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<AddressParamsDomain>() {
                override fun onSuccess(t: AddressParamsDomain) {
                    addressLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_ADDRESS_ERROR", e.message.toString())
                }

            })
    }

    //endregion

}