package com.example.data.network.api

import com.example.data.models.*
import com.example.data.models.auth.*
import com.example.data.models.families.FamilyData
import com.example.data.models.families.MemberData
import com.example.data.models.packs.HealthySetParamsData
import com.example.domain.models.ServerResponseDomain
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
    fun getFamilies(): Single<List<FamilyData>>

    @GET("users/families/{id}")
    fun getFamily(@Path("id") id: String): Single<FamilyData>

    @GET("users/families/{familyId}/members/{memberId}")
    fun getFamilyMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String
    ) : Single<MemberData>

    @GET("users/families/members/prefs")
    fun getPrefsAndAllergies() // TODO()что на выходе ??????????

    @POST("users/families")
    fun createFamily(@Body family: FamilyData): Single<FamilyData>

    @POST("users/families/{familyId}/members")
    fun createMember(
        @Path("familyId") familyId: String,
        @Body newFamilyMember: MemberData
    ) : Single<MemberData>

    @PATCH("users/families/{familyId}")
    fun updateFamily(
        @Path("familyId") familyId: String,
        @Body updatedFamily: FamilyData
    ) : Single<ServerResponseData>

    @PATCH("users/families/{familyId}/members/{memberId}")
    fun updateMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String,
        @Body updatedMember: MemberData
    ) : Single<ServerResponseData>

    @DELETE("users/families/{familyId}")
    fun deleteFamily(@Path("familyId") familyId: String) : Single<ServerResponseData>

    @DELETE("users/families/{familyId}/members/{memberId}")
    fun deleteMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String
    )  : Single<ServerResponseData>

    //endregion

    //region packs

    //TODO(адреса роутов вряд ли такие. выводы неизвестны)

    @GET("healthy-sets/info")
    fun getHealthySetParams() : Single<HealthySetParamsData>

    @GET("healthysets/params")
    fun getAllHealthySetParams() : Single<List<HealthySetParamsData>>

    @POST("healthy-sets/params")
    fun createHealthySetParams(
        @Body healthySetParams: HealthySetParamsData
    ) : Single<String>

    @PATCH("healthySetParams")
    fun updateHealthySetParams(
        @Body healthySetParams: HealthySetParamsData
    ) : Single<String>

    @PATCH("healthysets/{id}/replaceproduct")
    fun regenerateProduct(
        @Path("id") id: String
    ) : Single<String>

    @DELETE("healthySetParams/{id}")
    fun removeHealthySetParams(
        @Path("id") id: String
    ) : Single<String>

    //endregion

    //region addresses



    //endregion
}