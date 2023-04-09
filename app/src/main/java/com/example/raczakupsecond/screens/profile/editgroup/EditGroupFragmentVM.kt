package com.example.raczakupsecond.screens.profile.editgroup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.families.*
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
    private val createMemberUseCase = CreateMemberUseCase(networkRepository)

    private val familyLiveData = MutableLiveData<NewFamilyDomain>()

    fun getFamilyLiveData() : LiveData<NewFamilyDomain> {
        return familyLiveData
    }

    private val membersLiveData = MutableLiveData<List<NewMemberUpdateDomain>>()

    fun getMembersLiveData() : LiveData<List<NewMemberUpdateDomain>> = membersLiveData

    private val members: MutableList<NewMemberUpdateDomain> = mutableListOf()

    fun addMember(member: NewMemberUpdateDomain) {
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
            .subscribe(object : DisposableSingleObserver<NewFamilyDomain>() {
                override fun onSuccess(t: NewFamilyDomain) {
                    familyLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("GET_FAMILY", e.message.toString())
                }

            })
    }

    fun updateFamily(
        familyId: String,
        updatedFamily: NewFamilyUpdateDomain
    ) {
        updateFamilyUseCase.invoke(
            familyId,
            updatedFamily
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewFamilyDomain>() {
                override fun onSuccess(t: NewFamilyDomain) {
                    Log.d("UPDATE_FAMILY", t.name)
                }

                override fun onError(e: Throwable) {
                    Log.d("UPDATE_FAMILY", e.message.toString())
                }

            })
    }

    fun deleteFamily(
        familyId: String
    ) {
        deleteFamilyUseCase.invoke(familyId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ServerResponseDomain>() {
                override fun onSuccess(t: ServerResponseDomain) {
                    Log.d("DELETE_GROUP", t.message)
                }

                override fun onError(e: Throwable) {
                    Log.d("DELETE_GROUP", e.message.toString())
                }

            })
    }

    fun createFamily(
        family: NewFamilyUpdateDomain,
        members: List<NewMemberUpdateDomain>
    ) {
        createFamilyUseCase.invoke(family)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewFamilyDomain>() {
                override fun onSuccess(t: NewFamilyDomain) {
                    Log.d("CREATE_FAMILY", t.id.toString())
                    for (i in members) {
                        createMember(t.id.toString(), i)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_FAMILY", e.message.toString())
                }

            })
    }

    fun createMember(
        familyId: String,
        newFamilyMember: NewMemberUpdateDomain
    ) {
        createMemberUseCase.invoke(familyId, newFamilyMember)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<NewMemberDomain>() {
                override fun onSuccess(t: NewMemberDomain) {
                    Log.d("CREATE_MEMBER", t.name)
                }

                override fun onError(e: Throwable) {
                    Log.d("CREATE_MEMBER", e.message.toString())
                }

            })
    }

}