package com.example.raczakupsecond.screens.packs.editpack

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.packs.HealthySetParamsDomain
import com.example.domain.usecase.families.GetFamiliesUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EditPackFragmentVM : ViewModel() {

    private var healthySetParamsDomain = HealthySetParamsDomain()

    private val networkRepository = App.getNetworkRepository()

    private val getFamiliesUseCase = GetFamiliesUseCase(networkRepository)

    private val allFamiliesLiveData = MutableLiveData<List<FamilyDomain>>()
    fun getAllFamiliesLiveData() : LiveData<List<FamilyDomain>> = allFamiliesLiveData

    fun getFamilies() {
        getFamiliesUseCase
            .invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: DisposableSingleObserver<List<FamilyDomain>>() {
                override fun onSuccess(t: List<FamilyDomain>) {
                    allFamiliesLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("EditPack--GetFamilies", e.message.toString())
                }

            })
    }

    fun changeHealthySet(param: String, value: Int) {
        when (param) {
            "addressId" -> healthySetParamsDomain.addressId = value
            "familyId" -> healthySetParamsDomain.familyId = value
            "budget" -> healthySetParamsDomain.budget = value
            "days" -> healthySetParamsDomain.days = value
            "shop" -> healthySetParamsDomain.shop = value
            else -> Log.d("HEALTHY_SET_PARAM_MISS", param)
        }
    }

    fun getHealthySet() : HealthySetParamsDomain = healthySetParamsDomain

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