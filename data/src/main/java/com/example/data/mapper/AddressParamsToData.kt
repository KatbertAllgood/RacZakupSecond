package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsData
import com.example.domain.models.addresses.AddressParamsDomain

class AddressParamsToData(
    private val addressParamsDomain: AddressParamsDomain
) {
    fun toData() = AddressParamsData(
        addressParamsDomain.id,
        addressParamsDomain.name,
        addressParamsDomain.latitude,
        addressParamsDomain.longitude,
        addressParamsDomain.street,
        addressParamsDomain.building,
        addressParamsDomain.flat,
        addressParamsDomain.floor,
        addressParamsDomain.entrance
    )
}