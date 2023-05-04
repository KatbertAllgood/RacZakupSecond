package com.example.data.mapper

import com.example.data.models.addresses.AddressParamsData
import com.example.domain.models.addresses.AddressParamsDomain

class AddressParamsToData(
    private val addressParamsDomain: AddressParamsDomain
) {
    fun toData() = AddressParamsData(
        addressParamsDomain.id,
        addressParamsDomain.user_id,
        addressParamsDomain.name,
        addressParamsDomain.country,
        addressParamsDomain.federal_district,
        addressParamsDomain.region,
        addressParamsDomain.region_type_full,
        addressParamsDomain.city,
        addressParamsDomain.city_type_full,
        addressParamsDomain.city_area,
        addressParamsDomain.settlement,
        addressParamsDomain.settlement_type_full,
        addressParamsDomain.street,
        addressParamsDomain.street_type_full,
        addressParamsDomain.house,
        addressParamsDomain.house_type_full,
        addressParamsDomain.block,
        addressParamsDomain.block_type_full,
        addressParamsDomain.flat,
        addressParamsDomain.entrance,
        addressParamsDomain.floor,
        addressParamsDomain.beltway_hit,
        addressParamsDomain.comment,
        addressParamsDomain.postal_code,
        LocationParamsToData(addressParamsDomain.location).toData(),
        addressParamsDomain.createdDate,
        addressParamsDomain.updatedDate,
        addressParamsDomain.version
    )
}