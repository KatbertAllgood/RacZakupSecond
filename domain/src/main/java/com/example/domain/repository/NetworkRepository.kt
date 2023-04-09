package com.example.domain.repository

import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.auth.*
import com.example.domain.models.families.*
import io.reactivex.Single

interface NetworkRepository {

    fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain>

    fun checkCode(code: CodeDomain): Single<CodeResponseDomain>

    fun refresh(): Single<RefreshResponseDomain>

    fun currentUser(): Single<CurrentUserResponseDomain>

    fun logout(): Single<LogoutResponseDomain>

    fun getFamilies(): Single<AllFamiliesDomain>

    fun getFamily(id: String): Single<NewFamilyDomain>

    fun getFamilyMembers(
        familyId: String
    ) : Single<List<NewMemberDomain>>

    fun updateFamily(
        familyId: String,
        updatedFamily: NewFamilyUpdateDomain
    ) : Single<NewFamilyDomain>

    fun updateMember(
        familyId: String,
        memberId: String,
        updatedMember: NewMemberUpdateDomain
    ) : Single<NewMemberDomain>

    fun deleteFamily(familyId: String) : Single<ServerResponseDomain>

    fun deleteMember(
        familyId: String,
        memberId: String
    ) : Single<ServerResponseDomain>

    fun createFamily(family: NewFamilyUpdateDomain): Single<NewFamilyDomain>

    fun createMember(
        familyId: String,
        newFamilyMember: NewMemberUpdateDomain
    ) : Single<NewMemberDomain>
}