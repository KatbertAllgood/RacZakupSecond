package com.example.domain.repository

import com.example.domain.models.CodeDomain
import com.example.domain.models.CodeResponseDomain
import com.example.domain.models.PhoneResponseDomain
import com.example.domain.models.PhoneDomain
import io.reactivex.Single

interface NetworkRepository {

    fun checkPhone(phone: PhoneDomain): Single<PhoneResponseDomain>

    fun checkCode(code: CodeDomain): Single<CodeResponseDomain>
}