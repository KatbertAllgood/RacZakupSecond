package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoData
import com.example.domain.models.geo.ResponseGeoDomain

class ResponseGeoToData(
    private val responseGeoDomain: ResponseGeoDomain
) {
    fun toData() = ResponseGeoData(
        responseGeoDomain.result,
        responseGeoDomain.country,
        responseGeoDomain.federal_district,
        responseGeoDomain.region,
        responseGeoDomain.region_type_full,
        responseGeoDomain.city,
        responseGeoDomain.city_type_full,
        responseGeoDomain.city_area,
        responseGeoDomain.settlement,
        responseGeoDomain.settlement_type_full,
        responseGeoDomain.street,
        responseGeoDomain.street_type_full,
        responseGeoDomain.house,
        responseGeoDomain.house_type_full,
        responseGeoDomain.block,
        responseGeoDomain.block_type_full,
        responseGeoDomain.beltway_hit,
        responseGeoDomain.postal_code,
        responseGeoDomain.lat,
        responseGeoDomain.lon,
    )
}