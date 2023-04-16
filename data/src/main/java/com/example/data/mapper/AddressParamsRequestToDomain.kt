package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsRequestData
import com.example.domain.models.addresses.AddressParamsRequestDomain

class AddressParamsRequestToDomain(
    private val addressParamsRequestData: AddressParamsRequestData
) {
    fun toDomain() = AddressParamsRequestDomain(
        addressParamsRequestData.name,
        addressParamsRequestData.country,
        addressParamsRequestData.region,
        addressParamsRequestData.district,
        addressParamsRequestData.city,
        addressParamsRequestData.locality,
        addressParamsRequestData.street,
        addressParamsRequestData.house_number,
        addressParamsRequestData.corpus,
        addressParamsRequestData.apartment,
        addressParamsRequestData.entrance,
        addressParamsRequestData.floor,
        addressParamsRequestData.comment,
        addressParamsRequestData.postal_code,
        addressParamsRequestData.lat,
        addressParamsRequestData.lon,
    )
}