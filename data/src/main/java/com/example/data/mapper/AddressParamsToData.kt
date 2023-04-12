package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsData
import com.example.domain.models.addresses.AddressParamsDomain

class AddressParamsToData(
    private val addressParamsDomain: AddressParamsDomain
) {
    fun toData() = AddressParamsData(
        addressParamsDomain.id,
        addressParamsDomain.name,
        addressParamsDomain.country,
        addressParamsDomain.region,
        addressParamsDomain.district,
        addressParamsDomain.city,
        addressParamsDomain.locality,
        addressParamsDomain.street,
        addressParamsDomain.house_number,
        addressParamsDomain.corpus,
        addressParamsDomain.apartment,
        addressParamsDomain.entrance,
        addressParamsDomain.floor,
        addressParamsDomain.comment,
        addressParamsDomain.postal_code,
        addressParamsDomain.lat,
        addressParamsDomain.lon,
    )
}