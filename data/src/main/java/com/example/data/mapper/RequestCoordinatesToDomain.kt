package com.example.data.mapper

import com.example.data.models.geo.RequestCoordinatesData
import com.example.domain.models.geo.RequestCoordinatesDomain

class RequestCoordinatesToDomain(
    private val requestCoordinatesData: RequestCoordinatesData
) {
    fun toDomain() = RequestCoordinatesDomain(
        requestCoordinatesData.lat,
        requestCoordinatesData.lon
    )
}