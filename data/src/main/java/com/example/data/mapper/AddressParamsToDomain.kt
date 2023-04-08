package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsData
import com.example.domain.models.addresses.AddressParamsDomain

class AddressParamsToDomain(
    private val addressParamsData: AddressParamsData
) {
    fun toDomain() = AddressParamsDomain(
        addressParamsData.id,
        addressParamsData.name,
        addressParamsData.latitude,
        addressParamsData.longitude,
        addressParamsData.street,
        addressParamsData.building,
        addressParamsData.flat,
        addressParamsData.floor,
        addressParamsData.entrance
    )
}