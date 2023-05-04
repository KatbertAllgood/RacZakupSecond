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
        addressParamsData.federal_district,
        addressParamsData.region,
        addressParamsData.region_type_full,
        addressParamsData.city,
        addressParamsData.city_type_full,
        addressParamsData.city_area,
        addressParamsData.settlement,
        addressParamsData.settlement_type_full,
        addressParamsData.street,
        addressParamsData.street_type_full,
        addressParamsData.house,
        addressParamsData.house_type_full,
        addressParamsData.block,
        addressParamsData.block_type_full,
        addressParamsData.flat,
        addressParamsData.entrance,
        addressParamsData.floor,
        addressParamsData.beltway_hit,
        addressParamsData.comment,
        addressParamsData.postal_code,
        LocationParamsToDomain(addressParamsData.location).toDomain(),
        addressParamsData.createdDate,
        addressParamsData.updatedDate,
        addressParamsData.version
    )
}