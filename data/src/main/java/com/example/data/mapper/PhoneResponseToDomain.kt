package com.example.data.mapper

import com.example.data.models.PhoneResponseData
import com.example.domain.models.PhoneResponseDomain

class PhoneResponseToDomain(
    private val phoneResponseData: PhoneResponseData
) {
    fun toDomain() = PhoneResponseDomain(
        phoneResponseData.status
    )
}