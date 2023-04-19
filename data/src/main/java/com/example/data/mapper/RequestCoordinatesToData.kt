package com.example.data.mapper

import com.example.data.models.geo.RequestCoordinatesData
import com.example.domain.models.geo.RequestCoordinatesDomain

class RequestCoordinatesToData(
    private val requestCoordinatesDomain: RequestCoordinatesDomain
) {
    fun toData() = RequestCoordinatesData(
        requestCoordinatesDomain.lat,
        requestCoordinatesDomain.lon
    )
}