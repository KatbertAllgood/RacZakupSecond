package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsRequestData
import com.example.domain.models.addresses.AddressParamsRequestDomain

class AddressParamsRequestToData(
    private val addressParamsRequestDomain: AddressParamsRequestDomain
) {
    fun toData() = AddressParamsRequestData(
        addressParamsRequestDomain.name,
        addressParamsRequestDomain.country,
        addressParamsRequestDomain.region,
        addressParamsRequestDomain.district,
        addressParamsRequestDomain.city,
        addressParamsRequestDomain.locality,
        addressParamsRequestDomain.street,
        addressParamsRequestDomain.house_number,
        addressParamsRequestDomain.corpus,
        addressParamsRequestDomain.apartment,
        addressParamsRequestDomain.entrance,
        addressParamsRequestDomain.floor,
        addressParamsRequestDomain.comment,
        addressParamsRequestDomain.postal_code,
        addressParamsRequestDomain.lat,
        addressParamsRequestDomain.lon,
    )
}