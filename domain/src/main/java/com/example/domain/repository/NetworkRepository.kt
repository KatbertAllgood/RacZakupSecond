package com.example.domain.repository

import com.example.domain.models.*
import io.reactivex.Single

interface NetworkRepository {

    fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain>

    fun checkCode(code: CodeDomain): Single<CodeResponseDomain>

    fun refresh(): Single<RefreshResponseDomain>

    fun currentUser(): Single<CurrentUserResponseDomain>

    fun logout(): Single<LogoutResponseDomain>
}