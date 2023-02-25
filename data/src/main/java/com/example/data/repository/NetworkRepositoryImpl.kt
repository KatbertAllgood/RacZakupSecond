package com.example.data.repository

import com.example.data.mapper.*
import com.example.data.network.api.NetworkService
import com.example.domain.models.*
import com.example.domain.repository.NetworkRepository
import io.reactivex.Single

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

    override fun refresh(): Single<RefreshResponseDomain> {
        return NetworkService.retrofitService.refresh().map {
            return@map RefreshResponseToDomain(it).toDomain()
        }
    }

    override fun currentUser(): Single<CurrentUserResponseDomain> {
        return NetworkService.retrofitService.currentUser().map {
            return@map CurrentUserResponseToDomain(it).toDomain()
        }
    }

    override fun logout(): Single<LogoutResponseDomain> {
        return NetworkService.retrofitService.logout().map {
            return@map LogoutResponseToDomain(it).toDomain()
        }
    }


}