package com.example.data.mapper

import com.example.data.models.auth.PhoneData
import com.example.domain.models.auth.PhoneDomain

class PhoneToDomain(
    private val phoneData: PhoneData
) {
    fun toDomain() = PhoneDomain(
        phoneData.phoneNumber
    )
}