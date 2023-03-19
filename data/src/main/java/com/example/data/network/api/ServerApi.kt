package com.example.data.network.api

import com.example.data.models.*
import com.example.data.models.auth.*
import com.example.data.models.families.FamilyData
import com.example.data.models.families.MemberData
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

    @GET("families")
    fun getFamilies(): Single<List<FamilyData>>

    @GET("families/{id}")
    fun getFamily(@Path("id") id: String): Single<FamilyData>

    @GET("families/{familyId}/members/{memberId}")
    fun getFamilyMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String
    ) : Single<MemberData>

    @GET("families/members/prefs")
    fun getPrefsAndAllergies() // что на выходе ??????????

    @POST("families")
    fun createFamily(@Body family: FamilyData): Single<FamilyData>

    @POST("families/{familyId}/members")
    fun createMember(
        @Path("familyId") familyId: String,
        @Body newFamilyMember: MemberData
    ) : Single<MemberData>

    @PATCH("families/{familyId}")
    fun updateFamily(
        @Path("familyId") familyId: String,
        @Body updatedFamily: FamilyData
    ) : Single<ServerResponseData>

    @PATCH("families/{familyId}/members/{memberId}")
    fun updateMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String,
        @Body updatedMember: MemberData
    ) : Single<ServerResponseData>

    @DELETE("families/{familyId}")
    fun deleteFamily(@Path("familyId") familyId: String) : Single<ServerResponseData>

    @DELETE("families/{familyId}/members/{memberId}")
    fun deleteMember(
        @Path("familyId") familyId: String,
        @Path("memberId") memberId: String
    )  : Single<ServerResponseData>

    //endregion
}