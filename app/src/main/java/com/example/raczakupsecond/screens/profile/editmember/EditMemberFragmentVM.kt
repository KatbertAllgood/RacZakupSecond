package com.example.raczakupsecond.screens.profile.editmember

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.families.MemberDomain
import com.example.domain.models.families.NewMemberDomain
import com.example.domain.models.families.NewMemberUpdateDomain
import com.example.domain.usecase.families.CreateMemberUseCase
import com.example.domain.usecase.families.DeleteMemberUseCase
import com.example.domain.usecase.families.GetFamilyMembersUseCase
import com.example.domain.usecase.families.UpdateMemberUseCase
import com.example.raczakupsecond.R
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.math.RoundingMode
import java.util.*

class EditMemberFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()

    private val getFamilyMembersUseCase = GetFamilyMembersUseCase(networkRepository)
    private val updateMemberUseCase = UpdateMemberUseCase(networkRepository)
    private val deleteMemberUseCase = DeleteMemberUseCase(networkRepository)
    private val createMemberUseCase = CreateMemberUseCase(networkRepository)

    private val memberLiveData = MutableLiveData<NewMemberDomain>()
    fun getMemberLiveData() : LiveData<NewMemberDomain> {
        return memberLiveData
    }

    private val imtLiveData = MutableLiveData<Double>()
    fun getImtLiveData() : LiveData<Double> {
        return imtLiveData
    }

    fun getFamilyMember(familyId: String, memberId: String) {
        getFamilyMembersUseCase.invoke(familyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<NewMemberDomain>>() {
                override fun onSuccess(t: List<NewMemberDomain>) {
                    for (i in t) {
                        if (i.id == memberId.toInt()) {
                            memberLiveData.value = i
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("GET-MEMBER-ERROR", e.message.toString())
                }

            })
    }

    fun updateMember(
        familyId: String,
        memberId: String,
        updatedMember: NewMemberUpdateDomain
    ) {
        updateMemberUseCase.invoke(
            familyId,
            memberId,
            updatedMember
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewMemberDomain>() {
                override fun onSuccess(t: NewMemberDomain) {
                    Log.d("UPDATE_MEMBER", t.name)
                }

                override fun onError(e: Throwable) {
                    Log.d("UPDATE_MEMBER", e.message.toString())
                }

            })
    }

    fun deleteMember(
        familyId: String,
        memberId: String
    ) {
        deleteMemberUseCase.invoke(
            familyId,
            memberId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ServerResponseDomain>() {
                override fun onSuccess(t: ServerResponseDomain) {
                    Log.d("DELETE_MEMBER", t.message)
                }

                override fun onError(e: Throwable) {
                    Log.d("DELETE_MEMBER", e.message.toString())
                }

            })
    }

    fun createMember(
        familyId: String,
        newFamilyMember: NewMemberUpdateDomain
    ) {
        createMemberUseCase.invoke(
            familyId,
            newFamilyMember
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewMemberDomain>() {
                override fun onSuccess(t: NewMemberDomain) {
                    Log.d("CREATE_MEMBER", t.id.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_MEMBER", e.message.toString())
                }

            })
    }

    fun calculateImt(weight: Int, height: Int) {
        val heightMetersSqrt : Double = (height / 100.0) * ((height / 100.0))
//        Log.d("meters", heightMetersSqrt.toString())
        val imt = weight / heightMetersSqrt
        imtLiveData.value = imt.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
//        Log.d("result", result.toString())
    }

    fun calculateAge(
        birthDay: String
    ) : Int {

        var year: Int = 0
        var month: Int = 0
        var day: Int = 0

        if ("." in birthDay) {
            year = birthDay.split(".")[2].toInt()
            month = birthDay.split(".")[1].toInt()
            day = birthDay.split(".")[0].toInt()
        } else if ("-" in birthDay) {
            year = birthDay.split("-")[0].toInt()
            month = birthDay.split("-")[1].toInt()
            day = birthDay.split("-")[2].toInt()
        }

//        Log.d("BIRTH", "year: $year\nmonth: $month\nday: $day")

        val today = Calendar.getInstance()
//        Log.d("CURRENT", "year: ${today.get(Calendar.YEAR)}\nmonth: ${today.get(Calendar.MONTH) + 1}\nday: ${today.get(Calendar.DAY_OF_MONTH)}")
        var age = today.get(Calendar.YEAR) - year

        if((today.get(Calendar.MONTH) + 1) == month) {
            if (today.get(Calendar.DAY_OF_MONTH) < day) {
                age--
            }
        } else if((today.get(Calendar.MONTH) + 1) < month) {
            age--
        }

        return age
    }

    fun setCurrentDate() : String {
        val today = Calendar.getInstance()
        Log.d("CURRENT_MONTH", "${today.get(Calendar.DAY_OF_MONTH)}.${today.get(Calendar.MONTH) + 1}.${today.get(Calendar.YEAR)}")
        return "${today.get(Calendar.DAY_OF_MONTH)}.${today.get(Calendar.MONTH) + 1}.${today.get(Calendar.YEAR)}"
    }

    fun showNumberPickerHeightDialog(
        editText: EditText,
        view: View,
        context: Context
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val picker: NumberPicker = view.findViewById(R.id.number_picker_1)
        picker.wrapSelectorWheel = false
        picker.minValue = 70
        picker.maxValue = 300
        picker.value = editText.text.toString().toInt()
        picker.setOnValueChangedListener { _picker, oldval, newval ->
            editText.setText((newval.toString()))
        }
        builder.create().show()
    }

    fun showNumberPickerWeightDialog(
        editText: EditText,
        view: View,
        context: Context
    ) {
        var kilo = editText.text.toString().toInt()

        val builder = AlertDialog.Builder(context)
        builder.setView(view)

        val picker1: NumberPicker = view.findViewById(R.id.number_picker_1)
        picker1.wrapSelectorWheel = false
        picker1.minValue = 1
        picker1.maxValue = 200
        picker1.value = kilo
        picker1.setOnValueChangedListener { _picker, oldval, newval ->
            kilo = newval
            editText.setText(kilo.toString())

        }
        builder.create().show()
    }

    fun updateProgressBars(
        progressBars: List<ProgressBar>,
        progressBarsFull: List<ProgressBar>,
        progressBarsSet: List<ProgressBar>
    ) {

        progressBars.forEach {it.visibility = View.VISIBLE}

        progressBarsFull.forEach {it.visibility = View.GONE}

        progressBarsSet[0].min = 0
        progressBarsSet[0].progress = 0
        progressBarsSet[0].max = 183

        progressBarsSet[1].min = 184
        progressBarsSet[1].progress = 184
        progressBarsSet[1].max = 248

        progressBarsSet[2].min = 249
        progressBarsSet[2].progress = 249
        progressBarsSet[2].max = 298

        progressBarsSet[3].min = 299
        progressBarsSet[3].progress = 299
        progressBarsSet[3].max = 400
    }

}