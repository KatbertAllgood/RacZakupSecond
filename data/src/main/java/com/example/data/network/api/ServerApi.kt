package com.example.data.network.api

import com.example.data.models.*
import com.example.data.models.addresses.AddressParamsData
import com.example.data.models.addresses.AddressParamsRequestData
import com.example.data.models.auth.*
import com.example.data.models.families.*
import com.example.data.models.geo.RequestCoordinatesData
import com.example.data.models.geo.RequestQueryData
import com.example.data.models.geo.ResponseGeoCoordinatesData
import com.example.data.models.geo.ResponseGeoData
import com.example.data.models.packs.HealthySetParamsRequestData
import com.example.data.models.packs.HealthySetParamsResponseData
import io.reactivex.Single
import retrofit2.http.*

interface ServerApi {

    //region auth

    @POST("auth/authenticate")
    fun checkPhone(@Body phone: PhoneData): Single<PhoneResponseData>

    @POST("auth/activate")
    fun checkCode(@Body code: CodeData): Single<CodeResponseData>

    @GET("auth/refresh")
    fun refresh(): Single<RefreshResponseData>

    @GET("auth/current-user")
    fun currentUser(): Single<CurrentUserResponseData>

    @POST("auth/logout")
    fun logout(): Single<LogoutResponseData>

    //endregion

    //region family

    @GET("users/families")
    fun getFamilies(): Single<AllFamiliesData>

    @GET("users/families/{id}")
    fun getFamily(@Path("id") id: String): Single<NewFamilyData>

    @GET("users/families/{familyId}/members")
    fun getFamilyMembers(
        @Path("familyId") familyId: String
    ) : Single<List<NewMemberData>>

    @GET("users/families/members/prefs")
    fun getPrefsAndAllergies() // TODO()что на выходе ??????????

    @POST("users/families")
    fun createFamily(@Body family: NewFamilyUpdateData): Single<NewFamilyData>

    @POST("users/families/{familyId}/members")
    fun createMember(
        @Path("familyId") familyId: String,
        @Body newFamilyMember: NewMemberUpdateData
    ) : Single<NewMemberData>

    @PATCH("users/families/{familyId}")
    fun updateFamily(
        @Path("familyId") familyId: String,
        @Body updatedFamily: NewFamilyUpdateData
    ) : Single<NewFamilyData>

    @PATCH("users/families/{familyId}/members/{memberId}")
    fun updateMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String,
        @Body updatedMember: NewMemberUpdateData
    ) : Single<NewMemberData>

    @DELETE("users/families/{familyId}")
    fun deleteFamily(@Path("familyId") familyId: String) : Single<ServerResponseData>

    @DELETE("users/families/{familyId}/members/{memberId}")
    fun deleteMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String
    )  : Single<ServerResponseData>

    //endregion

    //region packs

    @POST("products/healthy-sets/params")
    fun createHealthySetParams(
        @Body healthySetParams: HealthySetParamsRequestData
    ) : Single<HealthySetParamsResponseData>

    //endregion

    //region addresses

    @GET("users/address")
    fun getAllAddresses() : Single<List<AddressParamsData>>

    @GET("users/address/{addressId}")
    fun getAddress(
        @Path("addressId") addressId: String
    ) : Single<AddressParamsData>

    @POST("users/address")
    fun createAddress(
        @Body address: AddressParamsRequestData
    ) : Single<AddressParamsData>

    @PATCH("users/address/{addressId}")
    fun updateAddress(
        @Path("addressId") addressId: String,
        @Body address: AddressParamsRequestData
    ) : Single<AddressParamsData>

    @DELETE("users/address/{addressId}")
    fun deleteAddress(
        @Path("addressId") addressId: String
    ) : Single<AddressParamsData>

    //endregion

    //region geo

    @POST("users/geo/coordinates")
    fun resolveCoordinates(
        @Body coordinates: RequestCoordinatesData
    ) : Single<List<ResponseGeoCoordinatesData>>

    @POST("users/geo/query")
    fun resolveQuery(
        @Body query: RequestQueryData
    ) : Single<ResponseGeoData>

    //endregion
}