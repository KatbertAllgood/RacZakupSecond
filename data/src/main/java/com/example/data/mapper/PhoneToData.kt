package com.example.data.mapper

import com.example.data.models.auth.PhoneData
import com.example.domain.models.auth.PhoneDomain

class PhoneToData(
    private val phoneDomain: PhoneDomain
) {
    fun toData() = PhoneData(phoneDomain.phoneNumber)
}