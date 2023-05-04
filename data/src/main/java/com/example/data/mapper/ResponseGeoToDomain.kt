package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoData
import com.example.domain.models.geo.ResponseGeoDomain

class ResponseGeoToDomain(
    private val responseGeoData: ResponseGeoData
) {
    fun toDomain() = ResponseGeoDomain(
        responseGeoData.result,
        responseGeoData.country,
        responseGeoData.federal_district,
        responseGeoData.region,
        responseGeoData.region_type_full,
        responseGeoData.city,
        responseGeoData.city_type_full,
        responseGeoData.city_area,
        responseGeoData.settlement,
        responseGeoData.settlement_type_full,
        responseGeoData.street,
        responseGeoData.street_type_full,
        responseGeoData.house,
        responseGeoData.house_type_full,
        responseGeoData.block,
        responseGeoData.block_type_full,
        responseGeoData.beltway_hit,
        responseGeoData.postal_code,
        responseGeoData.lat,
        responseGeoData.lon,
    )
}