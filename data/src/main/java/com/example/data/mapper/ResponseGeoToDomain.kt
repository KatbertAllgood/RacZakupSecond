package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoData
import com.example.domain.models.geo.ResponseGeoDomain

class ResponseGeoToDomain(
    private val responseGeoData: ResponseGeoData
) {
    fun toDomain() = ResponseGeoDomain(
        responseGeoData.lat,
        responseGeoData.lon,
        responseGeoData.result,
        responseGeoData.postal_code,
        responseGeoData.country,
        responseGeoData.federal_district,
        responseGeoData.region_type_full,
        responseGeoData.region,
        responseGeoData.area,
        responseGeoData.city,
        responseGeoData.city_area,
        responseGeoData.city_district,
        responseGeoData.settlement_type_full,
        responseGeoData.settlement,
        responseGeoData.street,
        responseGeoData.stead_type_full,
        responseGeoData.stead,
        responseGeoData.house,
        responseGeoData.block,
    )
}