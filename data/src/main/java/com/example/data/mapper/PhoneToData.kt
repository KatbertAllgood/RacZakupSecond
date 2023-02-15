package com.example.data.mapper

import com.example.data.models.PhoneData
import com.example.domain.models.PhoneDomain

class PhoneToData(
    private val phoneDomain: PhoneDomain
) {
    fun toData() = PhoneData(phoneDomain.phoneNumber)
}