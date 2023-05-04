package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsRequestData
import com.example.domain.models.addresses.AddressParamsRequestDomain

class AddressParamsRequestToData(
    private val addressParamsRequestDomain: AddressParamsRequestDomain
) {
    fun toData() = AddressParamsRequestData(
        addressParamsRequestDomain.name,
        addressParamsRequestDomain.country,
        addressParamsRequestDomain.federal_district,
        addressParamsRequestDomain.region,
        addressParamsRequestDomain.region_type_full,
        addressParamsRequestDomain.city,
        addressParamsRequestDomain.city_type_full,
        addressParamsRequestDomain.city_area,
        addressParamsRequestDomain.settlement,
        addressParamsRequestDomain.settlement_type_full,
        addressParamsRequestDomain.street,
        addressParamsRequestDomain.street_type_full,
        addressParamsRequestDomain.house,
        addressParamsRequestDomain.house_type_full,
        addressParamsRequestDomain.block,
        addressParamsRequestDomain.block_type_full,
        addressParamsRequestDomain.flat,
        addressParamsRequestDomain.entrance,
        addressParamsRequestDomain.floor,
        addressParamsRequestDomain.beltway_hit,
        addressParamsRequestDomain.comment,
        addressParamsRequestDomain.postal_code,
        addressParamsRequestDomain.lat,
        addressParamsRequestDomain.lon,
    )
}