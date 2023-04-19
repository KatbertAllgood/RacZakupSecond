package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoData
import com.example.domain.models.geo.ResponseGeoDomain

class ResponseGeoToData(
    private val responseGeoDomain: ResponseGeoDomain
) {
    fun toData() = ResponseGeoData(
        responseGeoDomain.lat,
        responseGeoDomain.lon,
        responseGeoDomain.result,
        responseGeoDomain.postal_code,
        responseGeoDomain.country,
        responseGeoDomain.federal_district,
        responseGeoDomain.region_type_full,
        responseGeoDomain.region,
        responseGeoDomain.area,
        responseGeoDomain.city,
        responseGeoDomain.city_area,
        responseGeoDomain.city_district,
        responseGeoDomain.settlement_type_full,
        responseGeoDomain.settlement,
        responseGeoDomain.street,
        responseGeoDomain.stead_type_full,
        responseGeoDomain.stead,
        responseGeoDomain.house,
        responseGeoDomain.block,
    )
}