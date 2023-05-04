package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsRequestData
import com.example.domain.models.addresses.AddressParamsRequestDomain

class AddressParamsRequestToDomain(
    private val addressParamsRequestData: AddressParamsRequestData
) {
    fun toDomain() = AddressParamsRequestDomain(
        addressParamsRequestData.name,
        addressParamsRequestData.country,
        addressParamsRequestData.federal_district,
        addressParamsRequestData.region,
        addressParamsRequestData.region_type_full,
        addressParamsRequestData.city,
        addressParamsRequestData.city_type_full,
        addressParamsRequestData.city_area,
        addressParamsRequestData.settlement,
        addressParamsRequestData.settlement_type_full,
        addressParamsRequestData.street,
        addressParamsRequestData.street_type_full,
        addressParamsRequestData.house,
        addressParamsRequestData.house_type_full,
        addressParamsRequestData.block,
        addressParamsRequestData.block_type_full,
        addressParamsRequestData.flat,
        addressParamsRequestData.entrance,
        addressParamsRequestData.floor,
        addressParamsRequestData.beltway_hit,
        addressParamsRequestData.comment,
        addressParamsRequestData.postal_code,
        addressParamsRequestData.lat,
        addressParamsRequestData.lon,
    )
}