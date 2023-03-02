package com.example.data.mapper

import com.example.data.models.auth.PhoneResponseData
import com.example.domain.models.auth.PhoneResponseDomain

class PhoneResponseToDomain(
    private val phoneResponseData: PhoneResponseData
) {
    fun toDomain() = PhoneResponseDomain(
        phoneResponseData.status
    )
}