package com.example.data.network.api

import com.example.data.models.*
import io.reactivex.Single
import retrofit2.http.*

interface ServerApi {

    @POST("auth/authenticate")
    fun checkPhone(@Body phone: PhoneData): Single<PhoneResponseData>

    @POST("auth/activate")
    fun checkCode(@Body code: CodeData): Single<CodeResponseData>

    @GET("auth/refresh")
    fun refresh(): Single<RefreshResponseData>

    @GET("auth/current-user")
    fun currentUser(): Single<CurrentUserResponseData>

}