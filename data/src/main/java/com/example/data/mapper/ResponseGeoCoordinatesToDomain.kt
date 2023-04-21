package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoCoordinatesData
import com.example.domain.models.geo.ResponseGeoCoordinatesDomain

class ResponseGeoCoordinatesToDomain(
    private val responseGeoCoordinatesData: ResponseGeoCoordinatesData
){
    fun toDomain() = ResponseGeoCoordinatesDomain(
        responseGeoCoordinatesData.value,
        responseGeoCoordinatesData.unrestricted_value,
        ResponseGeoToDomain(responseGeoCoordinatesData.data).toDomain(),
    )
}