package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsData
import com.example.domain.models.addresses.AddressParamsDomain

class AddressParamsToDomain(
    private val addressParamsData: AddressParamsData
) {
    fun toDomain() = AddressParamsDomain(
        addressParamsData.id,
        addressParamsData.user_id,
        addressParamsData.name,
        addressParamsData.country,
        addressParamsData.region,
        addressParamsData.district,
        addressParamsData.city,
        addressParamsData.locality,
        addressParamsData.street,
        addressParamsData.house_number,
        addressParamsData.corpus,
        addressParamsData.apartment,
        addressParamsData.entrance,
        addressParamsData.floor,
        addressParamsData.comment,
        addressParamsData.postal_code,
        LocationParamsToDomain(addressParamsData.location).toDomain(),
        addressParamsData.createdDate,
        addressParamsData.updatedDate,
        addressParamsData.version
    )
}