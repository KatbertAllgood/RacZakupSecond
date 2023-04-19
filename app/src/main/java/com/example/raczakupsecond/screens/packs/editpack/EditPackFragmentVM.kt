package com.example.raczakupsecond.screens.packs.editpack

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.families.AllFamiliesDomain
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.usecase.address.GetAllAddressesUseCase
import com.example.domain.usecase.families.GetFamiliesUseCase
import com.example.domain.usecase.families.GetFamilyUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EditPackFragmentVM : ViewModel() {

    private var healthySetParamsRequestDomain = HealthySetParamsRequestDomain()

    private val networkRepository = App.getNetworkRepository()
    private val getFamiliesUseCase = GetFamiliesUseCase(networkRepository)
    private val getFamilyUseCase = GetFamilyUseCase(networkRepository)
    private val getAllAddressesUseCase = GetAllAddressesUseCase(networkRepository)

    private val families: MutableList<NewFamilyDomain> = mutableListOf()
    private var familiesCounting: Int = 0

    private val allFamiliesLiveData = MutableLiveData<List<NewFamilyDomain>>()
    fun getAllFamiliesLiveData() : LiveData<List<NewFamilyDomain>> = allFamiliesLiveData

    private val allAddressesLiveData = MutableLiveData<List<AddressParamsDomain>>()
    fun getAllAddressesLiveData() : LiveData<List<AddressParamsDomain>> = allAddressesLiveData

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
                    Log.d("EditPack--GetFamilies", e.message.toString())
                }

            })
    }

    fun getFamily(
        familyId: Int
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

    fun getAllAddresses() {
        getAllAddressesUseCase
            .invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<AddressParamsDomain>>() {
                override fun onSuccess(t: List<AddressParamsDomain>) {
                    allAddressesLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_ADD_ADDRESSES_ERROR", e.message.toString())
                }

            })
    }

    fun clearFamilies() {
        families.clear()
    }

//    fun changeHealthySet(param: String, value: Int) {
//        when (param) {
//            "addressId" -> healthySetParamsRequestDomain.addressId = value
//            "familyId" -> healthySetParamsRequestDomain.familyId = value
//            "budget" -> healthySetParamsRequestDomain.budget = value
//            "days" -> healthySetParamsRequestDomain.days = value
//            "shop" -> healthySetParamsRequestDomain.shop = value
//            else -> Log.d("HEALTHY_SET_PARAM_MISS", param)
//        }
//    }

    fun changeHealthySetAddressId(value: Int) {
        healthySetParamsRequestDomain.addressId = value
    }

    fun changeHealthySetFamilyId(value: Int) {
        healthySetParamsRequestDomain.familyId = value
    }

    fun changeHealthySetBudget(value: String) {
        healthySetParamsRequestDomain.budget = value
    }

    fun changeHealthySetDays(value: Int) {
        healthySetParamsRequestDomain.days = value
    }

    fun changeHealthySetShopId(value: Int) {
        healthySetParamsRequestDomain.shop = value
    }

    fun getHealthySet() : HealthySetParamsRequestDomain = healthySetParamsRequestDomain

    fun nextStep(
        currentButton: View,
        currentPointIcon: ImageView,
        nextPointIcon: ImageView,
        currentConstraintLayout: ConstraintLayout,
        nextConstraintLayout: ConstraintLayout,
        currentNumber: TextView,
        nextNumber: TextView,
        currentPointTextView: TextView,
        typeFace: Typeface?,
        context: Context
    ) {

        listOf(
            currentButton,
            currentNumber,
            currentConstraintLayout
        ).forEach { it.visibility = View.GONE }

        currentPointIcon.setImageResource(
            R.drawable.ic_rounded_checked
        )

        currentPointTextView.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.checked_text
            )
        )

        nextPointIcon.setImageResource(
            R.drawable.ic_rounded_current_point
        )

        nextConstraintLayout.visibility = View.VISIBLE

        nextNumber.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.point_current
            )
        )

        nextNumber.typeface = typeFace

    }

}