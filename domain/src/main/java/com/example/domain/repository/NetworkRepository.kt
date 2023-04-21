package com.example.domain.repository

import com.example.domain.models.ServerResponseDomain
import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.models.addresses.AddressParamsRequestDomain
import com.example.domain.models.auth.*
import com.example.domain.models.families.*
import com.example.domain.models.geo.RequestCoordinatesDomain
import com.example.domain.models.geo.RequestQueryDomain
import com.example.domain.models.geo.ResponseGeoCoordinatesDomain
import com.example.domain.models.geo.ResponseGeoDomain
import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain
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

    fun getAllAddresses() : Single<List<AddressParamsDomain>>

    fun getAddress(
        addressId: String
    ) : Single<AddressParamsDomain>

    fun createAddress(
        address: AddressParamsRequestDomain
    ) : Single<AddressParamsDomain>

    fun updateAddress(
        addressId: String,
        address: AddressParamsRequestDomain
    ) : Single<AddressParamsDomain>

    fun deleteAddress(
        addressId: String
    ) : Single<AddressParamsDomain>

    fun resolveCoordinates(
        coordinates: RequestCoordinatesDomain
    ) : Single<List<ResponseGeoCoordinatesDomain>>

    fun resolveQuery(
        query: RequestQueryDomain
    ) : Single<ResponseGeoDomain>

    fun createHealthySetParams(
        healthySetParams: HealthySetParamsRequestDomain
    ) : Single<HealthySetParamsResponseDomain>
}