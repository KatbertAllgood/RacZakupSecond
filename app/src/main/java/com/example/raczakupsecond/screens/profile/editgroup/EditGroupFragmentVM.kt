package com.example.raczakupsecond.screens.profile.editgroup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.MemberDomain
import com.example.domain.usecase.families.*
import com.example.raczakupsecond.app.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class EditGroupFragmentVM : ViewModel() {

    private val networkRepository = App.getNetworkRepository()

    private val getFamilyUseCase = GetFamilyUseCase(networkRepository)
    private val updateFamilyUseCase = UpdateFamilyUseCase(networkRepository)
    private val deleteFamilyUseCase = DeleteFamilyUseCase(networkRepository)
    private val createFamilyUseCase = CreateFamilyUseCase(networkRepository)

    private val familyLiveData = MutableLiveData<FamilyDomain>()

    fun getFamilyLiveData() : LiveData<FamilyDomain> {
        return familyLiveData
    }

    private val membersLiveData = MutableLiveData<List<MemberDomain>>()

    fun getMembersLiveData() : LiveData<List<MemberDomain>> = membersLiveData

    private val members: MutableList<MemberDomain> = mutableListOf()

    fun addMember(member: MemberDomain) {
        members.add(member)
        membersLiveData.value = members
    }
    fun removeMember(index: Int) {
        members.removeAt(index)
        membersLiveData.value = members
    }
    fun removeLastMember() {
        members.removeAt(members.size - 1)
        membersLiveData.value = members
    }

    fun getFamily(id: String) {

        getFamilyUseCase
            .invoke(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<FamilyDomain>() {
                override fun onSuccess(t: FamilyDomain) {
                    familyLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_FAMILY - ERROR: ", e.message.toString())
                }

            })
    }

    fun updateFamily(
        familyId: String,
        updatedFamily: FamilyDomain
    ) {
        updateFamilyUseCase.invoke(
            familyId,
            updatedFamily
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ServerResponseDomain>() {
                override fun onSuccess(t: ServerResponseDomain) {
                    Log.d("UPDATE_FAMILY", t.success.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("UPDATE_FAMILY", e.message.toString())
                }

            })
    }

    fun deleteFamily(
        familyId: String
    ) {
        deleteFamilyUseCase.invoke(
            familyId
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ServerResponseDomain>() {
                override fun onSuccess(t: ServerResponseDomain) {
                    Log.d("DELETE_GROUP", t.message.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("DELETE_GROUP", e.message.toString())
                }

            })
    }

    fun createFamily(
        family: FamilyDomain
    ) {
        createFamilyUseCase.invoke(family)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<FamilyDomain>() {
                override fun onSuccess(t: FamilyDomain) {
                    Log.d("CREATE_FAMILY", t.id.toString())
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_FAMILY", e.message.toString())
                }

            })
    }

}