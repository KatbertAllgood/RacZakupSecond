package com.example.data.mapper

import com.example.data.models.PhoneData
import com.example.domain.models.PhoneDomain

class PhoneToDomain(
    private val phoneData: PhoneData
) {
    fun toDomain() = PhoneDomain(
        phoneData.phoneNumber
    )
}