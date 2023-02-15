package com.example.data.mapper

import com.example.data.models.PhoneResponseData
import com.example.domain.models.PhoneResponseDomain

class PhoneResponseToData(
    private val phoneResponseDomain: PhoneResponseDomain
) {
    fun toData() = PhoneResponseData(
        phoneResponseDomain.status
    )
}