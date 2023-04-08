package com.example.raczakupsecond.screens.packs.checkpack

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.MemberDomain
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

    private val membersListLiveData = MutableLiveData<List<MemberDomain>>()
    fun getMembersListLiveData() = membersListLiveData

    fun getFamily(
        familyId: String
    ) {

        getFamilyUseCase.invoke(
            familyId,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<FamilyDomain>() {
                override fun onSuccess(t: FamilyDomain) {
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

    private val budgetLiveData = MutableLiveData<Int>()
    fun getBudgetLiveData() = budgetLiveData

    fun changeBudgetLiveData(budgetId: Int) { budgetLiveData.value = budgetId }

    //endregion

    //region

}