package com.example.data.mapper

import com.example.data.models.auth.PhoneResponseData
import com.example.domain.models.auth.PhoneResponseDomain

class PhoneResponseToData(
    private val phoneResponseDomain: PhoneResponseDomain
) {
    fun toData() = PhoneResponseData(
        phoneResponseDomain.status
    )
}