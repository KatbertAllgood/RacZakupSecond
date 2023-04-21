package com.example.data.mapper

import com.example.data.models.geo.ResponseGeoCoordinatesData
import com.example.domain.models.geo.ResponseGeoCoordinatesDomain

class ResponseGeoCoordinatesToData(
    private val responseGeoCoordinatesDomain: ResponseGeoCoordinatesDomain
) {
    fun toData() = ResponseGeoCoordinatesData(
        responseGeoCoordinatesDomain.value,
        responseGeoCoordinatesDomain.unrestricted_value,
        ResponseGeoToData(responseGeoCoordinatesDomain.data).toData(),
    )
}