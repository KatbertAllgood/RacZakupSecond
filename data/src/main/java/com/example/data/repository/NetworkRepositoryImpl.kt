package com.example.data.repository

import com.example.data.mapper.CodeResponseToDomain
import com.example.data.mapper.CodeToData
import com.example.data.mapper.PhoneResponseToDomain
import com.example.data.mapper.PhoneToData
import com.example.data.network.api.ServerApi
import com.example.domain.models.CodeDomain
import com.example.domain.models.CodeResponseDomain
import com.example.domain.models.PhoneResponseDomain
import com.example.domain.models.PhoneDomain
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepositoryImpl : NetworkRepository{

    companion object {
        private const val BASE_URL = "http://83.220.171.139/api/v1/"
    }

    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServerApi::class.java)

    override fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain> {
        val phoneData = PhoneToData(phone).toData()
        return retrofitService.checkPhone(phoneData).map {
            return@map PhoneResponseToDomain(it).toDomain()
        }
    }

    override fun checkCode(code: CodeDomain): Single<CodeResponseDomain> {
        val codeData = CodeToData(code).toData()
        return retrofitService.checkCode(codeData).map {
            return@map CodeResponseToDomain(it).toDomain()
        }
    }
}