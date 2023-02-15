package com.example.data.network.api

import com.example.data.models.CodeData
import com.example.data.models.CodeResponseData
import com.example.data.models.PhoneResponseData
import com.example.data.models.PhoneData
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {

    @POST("auth/authenticate")
    fun checkPhone(@Body phone: PhoneData): Single<PhoneResponseData>

    @POST("auth/activate")
    fun checkCode(@Body code: CodeData): Single<CodeResponseData>

}