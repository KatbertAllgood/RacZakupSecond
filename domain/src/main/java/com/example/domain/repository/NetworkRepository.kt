package com.example.domain.repository

import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.auth.*
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.MemberDomain
import io.reactivex.Single

interface NetworkRepository {

    fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain>

    fun checkCode(code: CodeDomain): Single<CodeResponseDomain>

    fun refresh(): Single<RefreshResponseDomain>

    fun currentUser(): Single<CurrentUserResponseDomain>

    fun logout(): Single<LogoutResponseDomain>

    fun getFamilies(): Single<List<FamilyDomain>>

    fun getFamily(id: String): Single<FamilyDomain>

    fun getFamilyMember(familyId: String, memberId: String): Single<MemberDomain>

    fun updateFamily(
        familyId: String,
        updatedFamily: FamilyDomain
    ) : Single<ServerResponseDomain>

    fun updateMember(
        familyId: String,
        memberId: String,
        updatedMember: MemberDomain
    ) : Single<ServerResponseDomain>

    fun deleteFamily(familyId: String) : Single<ServerResponseDomain>

    fun deleteMember(
        familyId: String,
        memberId: String
    ) : Single<ServerResponseDomain>

    fun createFamily(family: FamilyDomain) : Single<FamilyDomain>

    fun createMember(
        familyId: String,
        newFamilyMember: MemberDomain
    ) : Single<MemberDomain>
}