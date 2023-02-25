package com.example.data.repository

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.data.mapper.*
import com.example.data.network.api.NetworkService
import com.example.data.network.api.ServerApi
import com.example.data.network.api.interceptors.CookieInterceptor
import com.example.data.network.api.interceptors.RefreshInterceptor
import com.example.domain.models.*
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepositoryImpl : NetworkRepository{

    override fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain> {
        val phoneData = PhoneToData(phone).toData()
        return NetworkService.retrofitService.checkPhone(phoneData).map {
            return@map PhoneResponseToDomain(it).toDomain()
        }
    }

    override fun checkCode(code: CodeDomain): Single<CodeResponseDomain> {
        val codeData = CodeToData(code).toData()
        return NetworkService.retrofitService.checkCode(codeData).map {
            return@map CodeResponseToDomain(it).toDomain()
        }
    }

    override fun refresh(accessToken: String, refreshToken: String): Single<RefreshResponseDomain> {
         return NetworkService.retrofitService.refresh(accessToken, refreshToken).map {
             return@map RefreshResponseToDomain(it).toDomain()
         }
    }

}